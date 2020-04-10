package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public class EffectAggression extends Effect {
    public EffectAggression(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (_effected.isPlayer() && _effected != _effector) {
            _effected.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, _effector);
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
