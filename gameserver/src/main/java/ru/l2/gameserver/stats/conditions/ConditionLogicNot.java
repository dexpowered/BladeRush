package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionLogicNot extends Condition {
    private final Condition _condition;

    public ConditionLogicNot(final Condition condition) {
        _condition = condition;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return !_condition.test(env);
    }
}
