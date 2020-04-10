package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.dao.CharacterVariablesDAO;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.GameClient;
import ru.l2.gameserver.network.lineage2.GameClient.GameClientState;
import ru.l2.gameserver.network.lineage2.SecondPasswordAuth.SecondPasswordAuthUI;
import ru.l2.gameserver.network.lineage2.SecondPasswordAuth.SecondPasswordAuthUI.SecondPasswordAuthUIType;
import ru.l2.gameserver.network.lineage2.serverpackets.ActionFail;
import ru.l2.gameserver.network.lineage2.serverpackets.CharSelected;
import ru.l2.gameserver.network.lineage2.serverpackets.ExShowScreenMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ExShowScreenMessage.ScreenMessageAlign;
import ru.l2.gameserver.utils.AutoBan;

public class RequestGameStart extends L2GameClientPacket {
    private int _charSlot;

    @Override
    protected void readImpl() {
        _charSlot = readD();
    }

    @Override
    protected void runImpl() {
        final GameClient client = getClient();
        if (client.getActiveChar() != null) {
            return;
        }
        final int objId = client.getObjectIdForSlot(_charSlot);
        if (AutoBan.isBanned(objId)) {
            sendPacket(ActionFail.STATIC);
            return;
        }
        final String hwidLock = CharacterVariablesDAO.getInstance().getVar(objId, "hwidlock@");
        if (hwidLock != null && !hwidLock.isEmpty() && client.getHwid() != null && !client.getHwid().isEmpty() && !hwidLock.equalsIgnoreCase(client.getHwid())) {
            sendPacket(new ExShowScreenMessage("HWID is locked.", 10000, ScreenMessageAlign.TOP_CENTER, true));
            sendPacket(ActionFail.STATIC);
            return;
        }
        final String ipLock = CharacterVariablesDAO.getInstance().getVar(objId, "iplock@");
        if (ipLock != null && !ipLock.isEmpty() && client.getIpAddr() != null && !client.getIpAddr().isEmpty() && !ipLock.equalsIgnoreCase(client.getIpAddr())) {
            sendPacket(new ExShowScreenMessage("IP address is locked.", 10000, ScreenMessageAlign.TOP_CENTER, true));
            sendPacket(ActionFail.STATIC);
            return;
        }
        final Runnable doSelect = new RunnableImpl() {
            @Override
            public void runImpl() {
                final Player activeChar = client.loadCharFromDisk(_charSlot);
                if (activeChar == null) {
                    sendPacket(ActionFail.STATIC);
                    return;
                }
                if (activeChar.getAccessLevel() < 0) {
                    activeChar.setAccessLevel(0);
                }
                client.setState(GameClientState.IN_GAME);
                client.sendPacket(new CharSelected(activeChar, client.getSessionKey().playOkID1));
            }
        };
        if (Config.USE_SECOND_PASSWORD_AUTH && !client.isSecondPasswordAuthed()) {
            if (client.getSecondPasswordAuth().isSecondPasswordSet()) {
                if (client.getSecondPasswordAuth().getUI() == null) {
                    client.getSecondPasswordAuth().setUI(new SecondPasswordAuthUI(SecondPasswordAuthUIType.VERIFY));
                }
            } else if (client.getSecondPasswordAuth().getUI() == null) {
                client.getSecondPasswordAuth().setUI(new SecondPasswordAuthUI(SecondPasswordAuthUIType.CREATE));
            }
            client.getSecondPasswordAuth().getUI().verify(client, doSelect);
        } else {
            ThreadPoolManager.getInstance().execute(doSelect);
        }
    }
}
