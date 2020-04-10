package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.stats.Env;

public class ConditionZoneName extends Condition {
    private final String _zoneName;

    public ConditionZoneName(final String zoneName) {
        _zoneName = zoneName;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.isPlayer() && env.character.isInZone(_zoneName);
    }
}
