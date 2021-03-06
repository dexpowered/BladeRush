package ru.l2.gameserver.model.quest.startcondition.impl;

import org.apache.commons.lang3.ArrayUtils;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.base.Race;
import ru.l2.gameserver.model.quest.startcondition.ConditionList;
import ru.l2.gameserver.model.quest.startcondition.ICheckStartCondition;

/**
 * @author KilRoy
 */
public final class RaceCondition implements ICheckStartCondition {
    private final Race[] race;

    public RaceCondition(final Race... race) {
        this.race = race;
    }

    @Override
    public final ConditionList checkCondition(final Player player) {
        if (ArrayUtils.contains(race, player.getRace()))
            return ConditionList.NONE;
        return ConditionList.RACE;
    }
}