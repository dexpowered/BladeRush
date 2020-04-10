package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.Reflection;

public interface OnTeleportListener extends PlayerListener {
    void onTeleport(final Player p0, final int p1, final int p2, final int p3, final Reflection p4);
}
