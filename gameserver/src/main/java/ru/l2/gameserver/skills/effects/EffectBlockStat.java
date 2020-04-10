package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.skills.skillclasses.NegateStats;
import ru.l2.gameserver.stats.Env;

public class EffectBlockStat extends Effect {
    public EffectBlockStat(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        _effected.addBlockStats(((NegateStats) _skill).getNegateStats());
    }

    @Override
    public void onExit() {
        super.onExit();
        _effected.removeBlockStats(((NegateStats) _skill).getNegateStats());
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
