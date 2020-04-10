package ru.l2.gameserver.model.quest.startcondition.impl;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.startcondition.ConditionList;
import ru.l2.gameserver.model.quest.startcondition.ICheckStartCondition;

public final class PlayerLevelCondition implements ICheckStartCondition {
    private final int min;
    private final int max;

    public PlayerLevelCondition(final int min, final int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public final ConditionList checkCondition(final Player player) {
        if (player.getLevel() >= min && player.getLevel() <= max)
            return ConditionList.NONE;
        return ConditionList.LEVEL;
    }
}