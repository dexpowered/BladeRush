package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectBuffImmunity extends Effect {
    public EffectBuffImmunity(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        getEffected().startBuffImmunity();
    }

    @Override
    public void onExit() {
        super.onExit();
        getEffected().stopBuffImmunity();
    }

    @Override
    public boolean onActionTime() {
        return !_effected.isDead() && getSkill().isToggle();
    }
}
