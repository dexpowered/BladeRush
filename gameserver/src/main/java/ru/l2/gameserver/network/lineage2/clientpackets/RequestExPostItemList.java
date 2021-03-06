package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ExReplyPostItemList;

public class RequestExPostItemList extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isActionsDisabled()) {
            activeChar.sendActionFailed();
        }
        if (!Config.ALLOW_MAIL) {
            activeChar.sendMessage(new CustomMessage("mail.Disabled", activeChar));
            activeChar.sendActionFailed();
            return;
        }
        activeChar.sendPacket(new ExReplyPostItemList(activeChar));
    }
}
