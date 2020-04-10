package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.stats.Env;

public class ConditionTargetPlayerNotMe extends Condition {
    private final boolean _flag;

    public ConditionTargetPlayerNotMe(final boolean flag) {
        _flag = flag;
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature activeChar = env.character;
        final Creature target = env.target;
        return activeChar != null && activeChar != target == _flag;
    }
}
