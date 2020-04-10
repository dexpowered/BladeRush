package ru.l2.gameserver.skills.skillclasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class Default extends Skill {
    private static final Logger LOGGER = LoggerFactory.getLogger(Default.class);

    public Default(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (activeChar.isPlayer()) {
            activeChar.sendMessage(new CustomMessage("l2p.gameserver.skills.skillclasses.Default.NotImplemented", (Player) activeChar).addNumber(getId()).addString("" + getSkillType()));
        }
        LOGGER.warn("NOTDONE skill: " + getId() + ", used by" + activeChar);
        activeChar.sendActionFailed();
    }
}
