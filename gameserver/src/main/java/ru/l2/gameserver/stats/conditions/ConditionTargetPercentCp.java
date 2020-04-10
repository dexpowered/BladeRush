package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionTargetPercentCp extends Condition {
    private final double _cp;

    public ConditionTargetPercentCp(final int cp) {
        _cp = cp / 100.0;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.target != null && env.target.getCurrentCpRatio() <= _cp;
    }
}
