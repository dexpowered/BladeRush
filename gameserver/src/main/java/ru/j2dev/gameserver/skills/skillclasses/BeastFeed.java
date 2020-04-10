package ru.j2dev.gameserver.skills.skillclasses;

import ru.j2dev.gameserver.model.Creature;
import ru.j2dev.gameserver.model.Player;
import ru.j2dev.gameserver.model.Skill;
import ru.j2dev.gameserver.model.instances.FeedableBeastInstance;
import ru.j2dev.gameserver.templates.StatsSet;

import java.util.List;

public class BeastFeed extends Skill {
    public BeastFeed(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            if (target instanceof FeedableBeastInstance) {
                ((FeedableBeastInstance) target).onSkillUse((Player) activeChar, _id);
            }
        }
    }
}
