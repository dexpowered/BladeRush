package ru.l2.gameserver.network.telnet.commands;

import ru.l2.gameserver.Announcements;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.network.lineage2.components.ChatType;
import ru.l2.gameserver.network.lineage2.serverpackets.Say2;
import ru.l2.gameserver.network.telnet.TelnetCommand;
import ru.l2.gameserver.network.telnet.TelnetCommandHolder;

import java.util.LinkedHashSet;
import java.util.Set;

public class TelnetSay implements TelnetCommandHolder {
    private final Set<TelnetCommand> _commands;

    public TelnetSay() {
        (_commands = new LinkedHashSet<>()).add(new TelnetCommand("announce", "ann") {
            @Override
            public String getUsage() {
                return "announce <text>";
            }

            @Override
            public String handle(final String[] args) {
                if (args.length == 0) {
                    return null;
                }
                Announcements.getInstance().announceToAll(args[0]);
                return "Announcement sent.\n";
            }
        });
        _commands.add(new TelnetCommand("message", "msg") {
            @Override
            public String getUsage() {
                return "message <player> <text>";
            }

            @Override
            public String handle(final String[] args) {
                if (args.length < 2) {
                    return null;
                }
                final Player player = World.getPlayer(args[0]);
                if (player == null) {
                    return "Player not found.\n";
                }
                final Say2 cs = new Say2(0, ChatType.TELL, "[Admin]", args[1]);
                player.sendPacket(cs);
                return "Message sent.\n";
            }
        });
    }

    @Override
    public Set<TelnetCommand> getCommands() {
        return _commands;
    }
}
