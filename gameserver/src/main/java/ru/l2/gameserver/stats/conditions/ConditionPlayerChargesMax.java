package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerChargesMax extends Condition {
    private final int _maxCharges;

    public ConditionPlayerChargesMax(final int maxCharges) {
        _maxCharges = maxCharges;
    }

    @Override
    protected boolean testImpl(final Env env) {
        if (env.character == null || !env.character.isPlayer()) {
            return false;
        }
        if (env.character.getIncreasedForce() >= _maxCharges) {
            env.character.sendPacket(Msg.YOUR_FORCE_HAS_REACHED_MAXIMUM_CAPACITY);
            return false;
        }
        return true;
    }
}
