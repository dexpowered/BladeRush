package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.stats.Env;

public final class ConditionUsingItemType extends Condition {
    private final long _mask;

    public ConditionUsingItemType(final long mask) {
        _mask = mask;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.isPlayable() && (_mask & ((Playable) env.character).getWearedMask()) != 0x0L;
    }
}
