package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public class EffectNegateEffects extends Effect {
    public EffectNegateEffects(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onExit() {
        super.onExit();
    }

    @Override
    public boolean onActionTime() {
        for (final Effect e : _effected.getEffectList().getAllEffects()) {
            if (((!"none".equals(e.getStackType()) && (e.getStackType().equals(getStackType()) || e.getStackType().equals(getStackType2()))) || (!"none".equals(e.getStackType2()) && (e.getStackType2().equals(getStackType()) || e.getStackType2().equals(getStackType2())))) && e.getStackOrder() <= getStackOrder()) {
                e.exit();
            }
        }
        return false;
    }
}
