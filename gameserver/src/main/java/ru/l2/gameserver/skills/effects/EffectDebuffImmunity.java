package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectDebuffImmunity extends Effect {
    public EffectDebuffImmunity(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        getEffected().startDebuffImmunity();
    }

    @Override
    public void onExit() {
        super.onExit();
        getEffected().stopDebuffImmunity();
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
