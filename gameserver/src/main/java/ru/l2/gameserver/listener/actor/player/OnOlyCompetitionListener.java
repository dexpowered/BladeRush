package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.olympiad.OlympiadGame;

public interface OnOlyCompetitionListener extends PlayerListener {
    void onOlyCompetitionCompleted(final Player p0, final OlympiadGame p1, final boolean p2);
}
