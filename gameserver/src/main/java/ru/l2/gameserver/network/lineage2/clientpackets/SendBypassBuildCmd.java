package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.handler.admincommands.AdminCommandHandler;
import ru.l2.gameserver.model.Player;

public class SendBypassBuildCmd extends L2GameClientPacket {
    private String _command;

    @Override
    protected void readImpl() {
        _command = readS();
        if (_command != null) {
            _command = _command.trim();
        }
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        String cmd = _command;
        if (!cmd.contains("admin_")) {
            cmd = "admin_" + cmd;
        }
        AdminCommandHandler.getInstance().useAdminCommandHandler(activeChar, cmd);
    }
}
