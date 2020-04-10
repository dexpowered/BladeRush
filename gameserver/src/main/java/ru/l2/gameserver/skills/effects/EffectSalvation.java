package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectSalvation extends Effect {
    public EffectSalvation(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean checkCondition() {
        return getEffected().isPlayer() && super.checkCondition();
    }

    @Override
    public void onStart() {
        getEffected().setIsSalvation(true);
    }

    @Override
    public void onExit() {
        super.onExit();
        getEffected().setIsSalvation(false);
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
