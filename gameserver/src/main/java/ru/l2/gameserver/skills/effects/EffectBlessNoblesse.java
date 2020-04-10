package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectBlessNoblesse extends Effect {
    public EffectBlessNoblesse(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        getEffected().setIsBlessedByNoblesse(true);
    }

    @Override
    public void onExit() {
        super.onExit();
        getEffected().setIsBlessedByNoblesse(false);
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
