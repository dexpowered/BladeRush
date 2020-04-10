package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.stats.Env;

public class EffectUnAggro extends Effect {
    public EffectUnAggro(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (_effected.isNpc()) {
            ((NpcInstance) _effected).setUnAggred(true);
        }
    }

    @Override
    public void onExit() {
        super.onExit();
        if (_effected.isNpc()) {
            ((NpcInstance) _effected).setUnAggred(false);
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
