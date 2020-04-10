package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.tables.SkillTable;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class BuffCharger extends Skill {
    private final int _target;

    public BuffCharger(final StatsSet set) {
        super(set);
        _target = set.getInteger("targetBuff", 0);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature target : targets) {
            int level = 0;
            final List<Effect> el = target.getEffectList().getEffectsBySkillId(_target);
            if (el != null) {
                level = el.get(0).getSkill().getLevel();
            }
            final Skill next = SkillTable.getInstance().getInfo(_target, level + 1);
            if (next != null) {
                next.getEffects(activeChar, target, false, false);
            }
        }
    }
}
