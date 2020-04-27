package ru.l2.gameserver.handler.voicecommands.impl;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.SevenSignsFestival.SevenSignsFestival;
import ru.l2.gameserver.network.lineage2.GameClient;
import ru.l2.gameserver.network.lineage2.GameClient.GameClientState;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ActionFail;
import ru.l2.gameserver.network.lineage2.serverpackets.CharSelected;
import ru.l2.gameserver.network.lineage2.serverpackets.CharacterSelectionInfo;
import ru.l2.gameserver.network.lineage2.serverpackets.RestartResponse;
import ru.l2.gameserver.data.scripts.Functions;

public class Relog extends Functions implements IVoicedCommandHandler {
    private final String[] _commandList;

    public Relog() {
        _commandList = new String[]{"relog", "restart"};
    }

    @Override
    public String[] getVoicedCommandList() {
        return _commandList;
    }

    @Override
    public boolean useVoicedCommand(final String command, final Player activeChar, final String target) {
        if (!Config.ALT_ALLOW_RELOG_COMMAND) {
            return false;
        }
        if (!command.equals("relog") && !command.equals("restart")) {
            return false;
        }
        if (activeChar == null) {
            return false;
        }
        if (activeChar.isInObserverMode()) {
            activeChar.sendPacket(Msg.OBSERVERS_CANNOT_PARTICIPATE, RestartResponse.FAIL, ActionFail.STATIC);
            return false;
        }
        if (activeChar.isInCombat()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_RESTART_WHILE_IN_COMBAT, RestartResponse.FAIL, ActionFail.STATIC);
            return false;
        }
        if (activeChar.isFishing()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_DO_ANYTHING_ELSE_WHILE_FISHING, RestartResponse.FAIL, ActionFail.STATIC);
            return false;
        }
        if (activeChar.isBlocked() && !activeChar.isFlying()) {
            activeChar.sendMessage(new CustomMessage("l2p.gameserver.clientpackets.RequestRestart.OutOfControl", activeChar));
            activeChar.sendPacket(RestartResponse.FAIL, ActionFail.STATIC);
            return false;
        }
        if (activeChar.isFestivalParticipant() && SevenSignsFestival.getInstance().isFestivalInitialized()) {
            activeChar.sendMessage(new CustomMessage("l2p.gameserver.clientpackets.RequestRestart.Festival", activeChar));
            activeChar.sendPacket(RestartResponse.FAIL, ActionFail.STATIC);
            return false;
        }
        final GameClient client = activeChar.getNetConnection();
        if (client != null && client.isConnected()) {
            client.setState(GameClientState.AUTHED);
            synchronized (activeChar) {
                final int objId = activeChar.getObjectId();
                final Runnable doSelect = new RunnableImpl() {
                    @Override
                    public void runImpl() {
                        if (!client.isConnected() || !client.isAuthed()) {
                            return;
                        }
                        if (Config.USE_SECOND_PASSWORD_AUTH && !client.isSecondPasswordAuthed()) {
                            return;
                        }
                        final int slotIdx = client.getSlotForObjectId(objId);
                        if (slotIdx < 0) {
                            return;
                        }
                        final Player activeChar = client.loadCharFromDisk(slotIdx);
                        client.setState(GameClientState.IN_GAME);
                        client.sendPacket(new CharSelected(activeChar, client.getSessionKey().playOkID1));
                    }
                };
                activeChar.restart();
                final CharacterSelectionInfo cl = new CharacterSelectionInfo(client.getLogin(), client.getSessionKey().playOkID1);
                client.sendPacket(RestartResponse.OK, cl);
                client.setCharSelection(cl.getCharInfo());
                ThreadPoolManager.getInstance().schedule(doSelect, 333L);
            }
            return true;
        }
        return false;
    }
}
