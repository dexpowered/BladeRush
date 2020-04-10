package ru.l2.gameserver.handler.voicecommands;

import ru.l2.gameserver.model.Player;

public interface IVoicedCommandHandler {
    boolean useVoicedCommand(final String p0, final Player p1, final String p2);

    String[] getVoicedCommandList();
}
