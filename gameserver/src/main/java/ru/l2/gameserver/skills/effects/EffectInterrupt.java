package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public class EffectInterrupt extends Effect {
    public EffectInterrupt(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!getEffected().isRaid()) {
            getEffected().abortCast(false, true);
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
