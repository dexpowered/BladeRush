package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionTargetMobId extends Condition {
    private final int _mobId;

    public ConditionTargetMobId(final int mobId) {
        _mobId = mobId;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.target != null && env.target.getNpcId() == _mobId;
    }
}
