package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;
import ru.l2.gameserver.stats.Stats;

public class EffectCombatPointHealOverTime extends Effect {
    public EffectCombatPointHealOverTime(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean onActionTime() {
        if (_effected.isHealBlocked()) {
            return true;
        }
        final double addToCp = Math.max(0.0, Math.min(calc(), _effected.calcStat(Stats.CP_LIMIT, null, null) * _effected.getMaxCp() / 100.0 - _effected.getCurrentCp()));
        if (addToCp > 0.0) {
            _effected.setCurrentCp(_effected.getCurrentCp() + addToCp);
        }
        return true;
    }
}
