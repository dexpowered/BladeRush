package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.stats.Env;
import ru.l2.gameserver.stats.Stats;

public class EffectManaHealPercent extends Effect {
    private final boolean _ignoreMpEff;

    public EffectManaHealPercent(final Env env, final EffectTemplate template) {
        super(env, template);
        _ignoreMpEff = template.getParam().getBool("ignoreMpEff", true);
    }

    @Override
    public boolean checkCondition() {
        return !_effected.isHealBlocked() && super.checkCondition();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (_effected.isHealBlocked()) {
            return;
        }
        final double mp = calc() * _effected.getMaxMp() / 100.0;
        final double newMp = mp * (_ignoreMpEff ? 100.0 : _effected.calcStat(Stats.MANAHEAL_EFFECTIVNESS, 100.0, _effector, getSkill())) / 100.0;
        final double addToMp = Math.max(0.0, Math.min(newMp, _effected.calcStat(Stats.MP_LIMIT, null, null) * _effected.getMaxMp() / 100.0 - _effected.getCurrentMp()));
        _effected.sendPacket(new SystemMessage(1068).addNumber(Math.round(addToMp)));
        if (addToMp > 0.0) {
            _effected.setCurrentMp(addToMp + _effected.getCurrentMp());
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
