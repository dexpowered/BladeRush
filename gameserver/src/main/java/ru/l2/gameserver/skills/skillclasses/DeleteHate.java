package ru.l2.gameserver.skills.skillclasses;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class DeleteHate extends Skill {
    public DeleteHate(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target != null) {
                if (target.isRaid()) {
                    continue;
                }
                if (getActivateRate() > 0) {
                    if (activeChar.isPlayer() && ((Player) activeChar).isGM()) {
                        activeChar.sendMessage(new CustomMessage("l2p.gameserver.skills.Formulas.Chance", (Player) activeChar).addString(getName()).addNumber(getActivateRate()));
                    }
                    if (!Rnd.chance(getActivateRate())) {
                        return;
                    }
                }
                if (target.isNpc()) {
                    final NpcInstance npc = (NpcInstance) target;
                    npc.getAggroList().clear(false);
                    npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
                }
                getEffects(activeChar, target, false, false);
            }
        }
    }
}
