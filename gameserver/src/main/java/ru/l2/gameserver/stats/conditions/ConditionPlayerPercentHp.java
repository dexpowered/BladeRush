package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionPlayerPercentHp extends Condition {
    private final double _hp;

    public ConditionPlayerPercentHp(final int hp) {
        _hp = hp / 100.0;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.getCurrentHpRatio() <= _hp;
    }
}
