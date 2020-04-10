package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.handler.usercommands.IUserCommandHandler;
import ru.l2.gameserver.handler.usercommands.UserCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;

public class BypassUserCmd extends L2GameClientPacket {
    private int _command;

    @Override
    protected void readImpl() {
        _command = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final IUserCommandHandler handler = UserCommandHandler.getInstance().getUserCommandHandler(_command);
        if (handler == null) {
            activeChar.sendMessage(new CustomMessage("common.S1NotImplemented", activeChar).addString(String.valueOf(_command)));
        } else {
            handler.useUserCommand(_command, activeChar);
        }
    }
}
