package handler.admincommands;

import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;

public class AdminGlobalEvent extends ScriptAdminCommand {
    @Override
    public boolean useAdminCommand(final Enum comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands c = (Commands) comm;
        switch (c) {
            case admin_list_events: {
                final GameObject object = activeChar.getTarget();
                if (object == null) {
                    activeChar.sendPacket(SystemMsg.INVALID_TARGET);
                    break;
                }
                object.getEvents().stream().map(e -> "- " + e).forEach(activeChar::sendMessage);
                break;
            }
        }
        return false;
    }

    @Override
    public Enum[] getAdminCommandEnum() {
        return Commands.values();
    }

    enum Commands {
        admin_list_events
    }
}
