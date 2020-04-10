package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.QuestState;

public interface OnQuestStateChangeListener extends PlayerListener {
    void onQuestStateChange(final Player p0, final QuestState p1);
}
