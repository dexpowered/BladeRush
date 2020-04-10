package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.stats.Stats;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class CombatPointHeal extends Skill {
    private final boolean _ignoreCpEff;

    public CombatPointHeal(final StatsSet set) {
        super(set);
        _ignoreCpEff = set.getBool("ignoreCpEff", false);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null) {
                if (target.isDead()) {
                    continue;
                }
                if (target.isHealBlocked()) {
                    continue;
                }
                final double maxNewCp = _power * (_ignoreCpEff ? 100.0 : target.calcStat(Stats.CPHEAL_EFFECTIVNESS, 100.0, activeChar, this)) / 100.0;
                final double addToCp = Math.max(0.0, Math.min(maxNewCp, target.calcStat(Stats.CP_LIMIT, null, null) * target.getMaxCp() / 100.0 - target.getCurrentCp()));
                if (addToCp > 0.0) {
                    target.setCurrentCp(addToCp + target.getCurrentCp());
                }
                target.sendPacket(new SystemMessage(1405).addNumber((long) addToCp));
                getEffects(activeChar, target, getActivateRate() > 0, false);
            }
        }
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
