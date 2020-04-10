package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.instances.TrapInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.NpcInfo;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class DetectTrap extends Skill {
    public DetectTrap(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null && target.isTrap()) {
                final TrapInstance trap = (TrapInstance) target;
                if (trap.getLevel() > getPower()) {
                    continue;
                }
                trap.setDetected(true);
                for (final Player player : World.getAroundPlayers(trap)) {
                    player.sendPacket(new NpcInfo(trap, player));
                }
            }
        }
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
