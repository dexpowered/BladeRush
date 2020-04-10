package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

public interface OnPlayerExitListener extends PlayerListener {
    void onPlayerExit(final Player p0);
}
