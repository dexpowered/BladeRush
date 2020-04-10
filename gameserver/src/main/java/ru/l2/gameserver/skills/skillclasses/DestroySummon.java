package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.Summon;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.stats.Formulas;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class DestroySummon extends Skill {
    public DestroySummon(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null) {
                if (getActivateRate() > 0 && !Formulas.calcSkillSuccess(activeChar, target, this, getActivateRate())) {
                    activeChar.sendPacket(new SystemMessage(139).addString(target.getName()).addSkillName(getId(), getLevel()));
                } else {
                    if (!target.isSummon()) {
                        continue;
                    }
                    ((Summon) target).saveEffects();
                    ((Summon) target).unSummon();
                    getEffects(activeChar, target, getActivateRate() > 0, false);
                }
            }
        }
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
