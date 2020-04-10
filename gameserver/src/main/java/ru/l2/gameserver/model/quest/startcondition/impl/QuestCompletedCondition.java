package ru.l2.gameserver.model.quest.startcondition.impl;


import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.startcondition.ConditionList;
import ru.l2.gameserver.model.quest.startcondition.ICheckStartCondition;

public final class QuestCompletedCondition implements ICheckStartCondition {
    private final String questId;

    public QuestCompletedCondition(final String questId) {
        this.questId = questId;
    }

    @Override
    public final ConditionList checkCondition(final Player player) {
        if (player.isQuestCompleted(questId))
            return ConditionList.NONE;
        return ConditionList.QUEST;
    }
}