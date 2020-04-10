package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.entity.Reflection;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerInstanceZone extends Condition {
    private final int _id;

    public ConditionPlayerInstanceZone(final int id) {
        _id = id;
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Reflection ref = env.character.getReflection();
        return ref.getInstancedZoneId() == _id;
    }
}
