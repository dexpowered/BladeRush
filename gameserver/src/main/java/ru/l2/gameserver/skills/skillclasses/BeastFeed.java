package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.FeedableBeastInstance;
import ru.l2.gameserver.templates.StatsSet;

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
