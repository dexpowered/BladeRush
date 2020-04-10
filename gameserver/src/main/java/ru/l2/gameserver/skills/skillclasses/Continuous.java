package ru.l2.gameserver.skills.skillclasses;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.stats.Stats;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class Continuous extends Skill {
    private final int _lethal1;
    private final int _lethal2;

    public Continuous(final StatsSet set) {
        super(set);
        _lethal1 = set.getInteger("lethal1", 0);
        _lethal2 = set.getInteger("lethal2", 0);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null) {
                if (getSkillType() == SkillType.BUFF && target != activeChar) {
                    if (target.isCursedWeaponEquipped()) {
                        continue;
                    }
                    if (activeChar.isCursedWeaponEquipped()) {
                        continue;
                    }
                }
                final boolean reflected = target.checkReflectSkill(activeChar, this);
                final Creature realTarget = reflected ? activeChar : target;
                final double mult = 0.01 * realTarget.calcStat(Stats.DEATH_VULNERABILITY, activeChar, this);
                final double lethal1 = _lethal1 * mult;
                final double lethal2 = _lethal2 * mult;
                if (lethal1 > 0.0 && Rnd.chance(lethal1)) {
                    if (realTarget.isPlayer()) {
                        realTarget.reduceCurrentHp(realTarget.getCurrentCp(), activeChar, this, true, true, false, true, false, false, true);
                        realTarget.sendPacket(SystemMsg.LETHAL_STRIKE);
                        activeChar.sendPacket(SystemMsg.YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL);
                    } else if (realTarget.isNpc() && !realTarget.isLethalImmune()) {
                        realTarget.reduceCurrentHp(realTarget.getCurrentHp() / 2.0, activeChar, this, true, true, false, true, false, false, true);
                        activeChar.sendPacket(SystemMsg.YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL);
                    }
                } else if (lethal2 > 0.0 && Rnd.chance(lethal2)) {
                    if (realTarget.isPlayer()) {
                        realTarget.reduceCurrentHp(realTarget.getCurrentHp() + realTarget.getCurrentCp() - 1.0, activeChar, this, true, true, false, true, false, false, true);
                        realTarget.sendPacket(SystemMsg.LETHAL_STRIKE);
                        activeChar.sendPacket(SystemMsg.YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL);
                    } else if (realTarget.isNpc() && !realTarget.isLethalImmune()) {
                        realTarget.reduceCurrentHp(realTarget.getCurrentHp() - 1.0, activeChar, this, true, true, false, true, false, false, true);
                        activeChar.sendPacket(SystemMsg.YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL);
                    }
                }
                getEffects(activeChar, target, getActivateRate() > 0, false, reflected);
            }
        }
        if (isSSPossible() && (!Config.SAVING_SPS || _skillType != SkillType.BUFF)) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
