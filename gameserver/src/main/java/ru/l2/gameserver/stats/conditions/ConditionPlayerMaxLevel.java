package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionPlayerMaxLevel extends Condition {
    private final int _level;

    public ConditionPlayerMaxLevel(final int level) {
        _level = level;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.getLevel() <= _level;
    }
}
