package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.stats.Env;

public class ConditionTargetAggro extends Condition {
    private final boolean _isAggro;

    public ConditionTargetAggro(final boolean isAggro) {
        _isAggro = isAggro;
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature target = env.target;
        if (target == null) {
            return false;
        }
        if (target.isMonster()) {
            return ((MonsterInstance) target).isAggressive() == _isAggro;
        }
        return target.isPlayer() && target.getKarma() > 0;
    }
}
