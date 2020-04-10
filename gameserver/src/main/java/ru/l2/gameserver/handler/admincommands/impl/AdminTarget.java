package ru.l2.gameserver.handler.admincommands.impl;

import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;

public class AdminTarget implements IAdminCommandHandler {
    @Override
    public boolean useAdminCommand(final Enum<?> comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands command = (Commands) comm;
        if (!activeChar.getPlayerAccess().CanViewChar) {
            return false;
        }
        try {
            final String targetName = wordList[1];
            final GameObject obj = World.getPlayer(targetName);
            if (obj != null && obj.isPlayer()) {
                obj.onAction(activeChar, false);
            } else {
                activeChar.sendMessage("Player " + targetName + " not found");
            }
        } catch (IndexOutOfBoundsException e) {
            activeChar.sendMessage("Please specify correct name.");
        }
        return true;
    }

    @Override
    public Enum[] getAdminCommandEnum() {
        return Commands.values();
    }

    private enum Commands {
        admin_target
    }
}
