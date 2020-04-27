package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.TrapInstance;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class DefuseTrap extends Skill {
    public DefuseTrap(final StatsSet set) {
        super(set);
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        if (target == null || !target.isTrap()) {
            activeChar.sendPacket(Msg.INVALID_TARGET);
            return false;
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null && target.isTrap()) {
                final TrapInstance trap = (TrapInstance) target;
                if (trap.getLevel() > getPower()) {
                    continue;
                }
                trap.deleteMe();
            }
        }
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
