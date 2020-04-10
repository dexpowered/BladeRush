package ru.l2.gameserver.model.quest.startcondition.impl;

import org.apache.commons.lang3.ArrayUtils;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.base.ClassId;
import ru.l2.gameserver.model.quest.startcondition.ConditionList;
import ru.l2.gameserver.model.quest.startcondition.ICheckStartCondition;

/**
 * @author KilRoy
 */
public final class ClassIdCondition implements ICheckStartCondition {
    private final ClassId[] classId;

    public ClassIdCondition(final ClassId... classId) {
        this.classId = classId;
    }

    @Override
    public final ConditionList checkCondition(final Player player) {
        if (ArrayUtils.contains(classId, player.getClassId()))
            return ConditionList.NONE;
        return ConditionList.CLASS_ID;
    }
}