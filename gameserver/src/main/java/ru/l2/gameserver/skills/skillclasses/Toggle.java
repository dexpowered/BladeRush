package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class Toggle extends Skill {
    public Toggle(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (activeChar.getEffectList().getEffectsBySkillId(_id) != null) {
            activeChar.getEffectList().stopEffect(_id);
            activeChar.sendActionFailed();
            return;
        }
        getEffects(activeChar, activeChar, getActivateRate() > 0, false);
    }
}
