package ru.l2.gameserver.handler.petition;

import ru.l2.gameserver.model.Player;

public interface IPetitionHandler {
    void handle(final Player p0, final int p1, final String p2);
}
