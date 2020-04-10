package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.AggroList.AggroInfo;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class ShiftAggression extends Skill {
    public ShiftAggression(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (activeChar.getPlayer() == null) {
            return;
        }
        for (final Creature target : targets) {
            if (target != null) {
                if (!target.isPlayer()) {
                    continue;
                }
                final Player player = (Player) target;
                for (final NpcInstance npc : World.getAroundNpc(activeChar, getSkillRadius(), getSkillRadius())) {
                    final AggroInfo ai = npc.getAggroList().get(activeChar);
                    if (ai == null) {
                        continue;
                    }
                    npc.getAggroList().addDamageHate(player, 0, ai.hate);
                    npc.getAggroList().remove(activeChar, true);
                }
            }
        }
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
    }
}
