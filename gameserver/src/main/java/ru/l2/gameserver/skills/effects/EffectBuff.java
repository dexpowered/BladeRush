package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectBuff extends Effect {
    public EffectBuff(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
