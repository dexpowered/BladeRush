package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.stats.Env;

public class ConditionTargetSummon extends Condition {
    private final boolean _flag;

    public ConditionTargetSummon(final boolean flag) {
        _flag = flag;
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature target = env.target;
        return target != null && target.isSummon() == _flag;
    }
}
