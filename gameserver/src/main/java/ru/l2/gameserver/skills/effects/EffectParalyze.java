package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectParalyze extends Effect {
    public EffectParalyze(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean checkCondition() {
        return !_effected.isParalyzeImmune() && super.checkCondition();
    }

    @Override
    public void onStart() {
        super.onStart();
        _effected.startParalyzed();
        _effected.abortAttack(true, true);
        _effected.abortCast(true, true);
    }

    @Override
    public void onExit() {
        super.onExit();
        _effected.stopParalyzed();
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
