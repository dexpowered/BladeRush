package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

public interface OnPlayerPartyLeaveListener extends PlayerListener {
    void onPartyLeave(final Player p0);
}
