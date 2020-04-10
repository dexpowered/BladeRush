package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class PcBangPointsAdd extends Skill {
    public PcBangPointsAdd(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        final int points = (int) _power;
        for (final Creature target : targets) {
            if (target.isPlayer()) {
                final Player player = target.getPlayer();
                player.addPcBangPoints(points, false);
            }
            getEffects(activeChar, target, getActivateRate() > 0, false);
        }
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
