package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Summon;
import ru.l2.gameserver.stats.Env;

public final class EffectDestroySummon extends Effect {
    public EffectDestroySummon(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean checkCondition() {
        return _effected.isSummon() && super.checkCondition();
    }

    @Override
    public void onStart() {
        super.onStart();
        ((Summon) _effected).unSummon();
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
