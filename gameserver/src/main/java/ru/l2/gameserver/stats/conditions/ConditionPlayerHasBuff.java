package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.skills.EffectType;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerHasBuff extends Condition {
    private final EffectType _effectType;
    private final int _level;

    public ConditionPlayerHasBuff(final EffectType effectType, final int level) {
        _effectType = effectType;
        _level = level;
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature character = env.character;
        if (character == null) {
            return false;
        }
        final Effect effect = character.getEffectList().getEffectByType(_effectType);
        return effect != null && (_level == -1 || effect.getSkill().getLevel() >= _level);
    }
}
