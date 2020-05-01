package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.data.xml.holder.NpcTemplateHolder;
import ru.l2.gameserver.idfactory.IdFactory;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.AgathionInstance;
import ru.l2.gameserver.templates.StatsSet;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.Location;

import java.util.List;

public class AgathionSummon extends Skill {

    public AgathionSummon(final StatsSet set) {
        super(set);
        _npcId = set.getInteger("npcId", 1);
    }

    @Override
    public void useSkill(final Creature caster, final List<Creature> targets) {
        final Player activeChar = caster.getPlayer();
        final NpcTemplate summonTemplate = NpcTemplateHolder.getInstance().getTemplate(getNpcId());
        final AgathionInstance summon = new AgathionInstance(IdFactory.getInstance().getNextId(), summonTemplate);

        summon.setTargetable(false);
        summon.setShowName(false);
        summon.setIsInvul(true);
        summon.setHeading(activeChar.getHeading());
        summon.setReflection(activeChar.getReflection());
        summon.spawnMe(Location.findAroundPosition(activeChar, 50, 70));
        summon.setOwner(activeChar);
        summon.setIsRunning(true);
        summon.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, activeChar, 1600);

    }
}
