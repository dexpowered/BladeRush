package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.stats.Env;

public class EffectEnervation extends Effect {
    public EffectEnervation(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (_effected.isNpc()) {
            ((NpcInstance) _effected).setParameter("DebuffIntention", 0.5);
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }

    @Override
    public void onExit() {
        super.onExit();
        if (_effected.isNpc()) {
            ((NpcInstance) _effected).setParameter("DebuffIntention", 1.0);
        }
    }
}
