package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

public interface OnPlayerEnterListener extends PlayerListener {
    void onPlayerEnter(final Player p0);
}
