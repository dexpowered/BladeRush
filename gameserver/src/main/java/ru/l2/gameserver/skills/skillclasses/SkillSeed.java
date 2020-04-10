package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.skills.effects.EffectSkillSeed;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class SkillSeed extends Skill {
    public SkillSeed(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (activeChar.isAlikeDead()) {
            return;
        }
        for (final Creature target : targets) {
            if (target.isAlikeDead() && getTargetType() != SkillTargetType.TARGET_CORPSE) {
                continue;
            }
            final List<Effect> effects = target.getEffectList().getEffectsBySkill(this);
            boolean haveEffect = false;
            if (effects != null && !effects.isEmpty()) {
                for (final Effect effect : effects) {
                    if (!(effect instanceof EffectSkillSeed)) {
                        continue;
                    }
                    final EffectSkillSeed effectSeed = (EffectSkillSeed) effect;
                    effectSeed.incSeeds();
                    haveEffect = true;
                }
            }
            if (haveEffect) {
                continue;
            }
            getEffects(activeChar, target, false, false);
        }
    }
}
