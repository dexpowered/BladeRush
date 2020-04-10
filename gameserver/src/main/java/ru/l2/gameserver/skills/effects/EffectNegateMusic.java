package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public class EffectNegateMusic extends Effect {
    public EffectNegateMusic(final Env env, final EffectTemplate template) {
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
            if (e.getSkill().isMusic()) {
                e.exit();
            }
        }
        return false;
    }
}
