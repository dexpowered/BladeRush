package ru.l2.gameserver.handler.admincommands.impl;

import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.NpcHtmlMessage;

public class AdminHelpPage implements IAdminCommandHandler {
    public static void showHelpHtml(final Player targetChar, final String content) {
        final NpcHtmlMessage adminReply = new NpcHtmlMessage(5);
        adminReply.setHtml(content);
        targetChar.sendPacket(adminReply);
    }

    @Override
    public boolean useAdminCommand(final Enum<?> comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands command = (Commands) comm;
        if (!activeChar.getPlayerAccess().Menu) {
            return false;
        }
        switch (command) {
            case admin_showhtml: {
                if (wordList.length != 2) {
                    activeChar.sendMessage("Usage: //showhtml <file>");
                    return false;
                }
                activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/" + wordList[1]));
                break;
            }
        }
        return true;
    }

    @Override
    public Enum[] getAdminCommandEnum() {
        return Commands.values();
    }

    private enum Commands {
        admin_showhtml
    }
}
