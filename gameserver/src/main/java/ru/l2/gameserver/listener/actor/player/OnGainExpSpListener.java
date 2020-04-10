package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

public interface OnGainExpSpListener extends PlayerListener {
    void onGainExpSp(final Player p0, final long p1, final long p2);
}
