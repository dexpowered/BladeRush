package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionTargetMob extends Condition {
    private final boolean _isMob;

    public ConditionTargetMob(final boolean isMob) {
        _isMob = isMob;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.target != null && env.target.isMonster() == _isMob;
    }
}
