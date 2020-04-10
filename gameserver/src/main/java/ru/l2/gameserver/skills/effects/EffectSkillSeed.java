package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public final class EffectSkillSeed extends Effect {
    private int _seeds;

    public EffectSkillSeed(final Env env, final EffectTemplate template) {
        super(env, template);
        _seeds = 1;
    }

    public void incSeeds() {
        ++_seeds;
    }

    public int getSeeds() {
        return _seeds;
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
