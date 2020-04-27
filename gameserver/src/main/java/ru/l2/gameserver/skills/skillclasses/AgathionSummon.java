package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.data.xml.holder.NpcTemplateHolder;
import ru.l2.gameserver.idfactory.IdFactory;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.AgathionInstance;
import ru.l2.gameserver.templates.StatsSet;
import ru.l2.gameserver.templates.npc.NpcTemplate;

import java.util.List;

public class AgathionSummon extends Skill {

    public AgathionSummon(final StatsSet set) {
        super(set);
        _npcId = set.getInteger("npcId", 1);
    }

    @Override
    public void useSkill(final Creature caster, final List<Creature> targets) {
        final Player activeChar = caster.getPlayer();
        final NpcTemplate agathionTemplate = NpcTemplateHolder.getInstance().getTemplate(getNpcId());
        final AgathionInstance agathion = new AgathionInstance(IdFactory.getInstance().getNextId(), agathionTemplate);
        activeChar.setAgathion(agathion);
        agathion.setOwner(activeChar);
        agathion.setShowName(false);
        agathion.setTargetable(false);
        activeChar.sendMessage("agathion: owner: {}" +activeChar.getAgathion().getPlayer().getName());
    }
}
