package ru.l2.gameserver.model.quest.startcondition.impl;


import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.startcondition.ConditionList;
import ru.l2.gameserver.model.quest.startcondition.ICheckStartCondition;

public final class ClassLevelCondition implements ICheckStartCondition {
    private final int classLevels;

    public ClassLevelCondition(final int classLevels) {
        this.classLevels = classLevels;
    }

    @Override
    public final ConditionList checkCondition(final Player player) {
        return player.getClassId().getLevel() >= classLevels ? ConditionList.NONE : ConditionList.CLASS_LEVEL;
    }
}