package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.stats.Env;

public final class ConditionTargetHasForbiddenSkill extends Condition {
    private final int _skillId;

    public ConditionTargetHasForbiddenSkill(final int skillId) {
        _skillId = skillId;
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature target = env.target;
        return target.isPlayable() && target.getSkillLevel(_skillId) <= 0;
    }
}
