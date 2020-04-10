package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.SevenSignsFestival.SevenSignsFestival;
import ru.l2.gameserver.network.lineage2.GameClient.GameClientState;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ActionFail;
import ru.l2.gameserver.network.lineage2.serverpackets.CharacterSelectionInfo;
import ru.l2.gameserver.network.lineage2.serverpackets.RestartResponse;

public class RequestRestart extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isInObserverMode()) {
            activeChar.sendPacket(Msg.OBSERVERS_CANNOT_PARTICIPATE, RestartResponse.FAIL, ActionFail.STATIC);
            return;
        }
        if (activeChar.isInCombat()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_RESTART_WHILE_IN_COMBAT, RestartResponse.FAIL, ActionFail.STATIC);
            return;
        }
        if (activeChar.isFishing()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_DO_ANYTHING_ELSE_WHILE_FISHING, RestartResponse.FAIL, ActionFail.STATIC);
            return;
        }
        if (activeChar.isBlocked() && !activeChar.isFlying()) {
            activeChar.sendMessage(new CustomMessage("l2p.gameserver.clientpackets.RequestRestart.OutOfControl", activeChar));
            activeChar.sendPacket(RestartResponse.FAIL, ActionFail.STATIC);
            return;
        }
        if (activeChar.isFestivalParticipant() && SevenSignsFestival.getInstance().isFestivalInitialized()) {
            activeChar.sendMessage(new CustomMessage("l2p.gameserver.clientpackets.RequestRestart.Festival", activeChar));
            activeChar.sendPacket(RestartResponse.FAIL, ActionFail.STATIC);
            return;
        }
        if (getClient() != null) {
            getClient().setState(GameClientState.AUTHED);
        }
        activeChar.restart();
        final CharacterSelectionInfo cl = new CharacterSelectionInfo(getClient().getLogin(), getClient().getSessionKey().playOkID1);
        sendPacket(RestartResponse.OK, cl);
        getClient().setCharSelection(cl.getCharInfo());
    }
}
