package ru.l2.gameserver.stats.conditions;

import gnu.trove.set.hash.TIntHashSet;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.stats.Env;

public class ConditionTargetForbiddenClassId extends Condition {
    private final TIntHashSet _classIds;

    public ConditionTargetForbiddenClassId(final String[] ids) {
        _classIds = new TIntHashSet();
        for (final String id : ids) {
            _classIds.add(Integer.parseInt(id));
        }
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature target = env.target;
        return target.isPlayable() && (!target.isPlayer() || !_classIds.contains(target.getPlayer().getActiveClassId()));
    }
}
