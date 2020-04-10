package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

public interface OnPvpPkKillListener extends PlayerListener {
    void onPvpPkKill(final Player killer, final Player victim, final boolean isPk);
}
