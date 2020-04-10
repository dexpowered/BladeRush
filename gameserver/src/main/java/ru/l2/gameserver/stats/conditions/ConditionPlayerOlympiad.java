package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionPlayerOlympiad extends Condition {
    private final boolean _value;

    public ConditionPlayerOlympiad(final boolean v) {
        _value = v;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.isOlyParticipant() == _value;
    }
}
