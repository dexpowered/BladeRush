package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class DeathPenalty extends Skill {
    public DeathPenalty(final StatsSet set) {
        super(set);
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        if (activeChar.getKarma() > 0 && !Config.ALT_DEATH_PENALTY_C5_CHAOTIC_RECOVERY) {
            activeChar.sendActionFailed();
            return false;
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null) {
                if (!target.isPlayer()) {
                    continue;
                }
                ((Player) target).getDeathPenalty().reduceLevel();
            }
        }
    }
}
