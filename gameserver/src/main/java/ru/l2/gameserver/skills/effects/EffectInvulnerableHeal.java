package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.Skill.SkillType;
import ru.l2.gameserver.stats.Env;

public final class EffectInvulnerableHeal extends Effect {
    public EffectInvulnerableHeal(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean checkCondition() {
        if (_effected.isInvul()) {
            return false;
        }
        final Skill skill = _effected.getCastingSkill();
        return (skill == null || skill.getSkillType() != SkillType.TAKECASTLE) && super.checkCondition();
    }

    @Override
    public void onStart() {
        super.onStart();
        _effected.setIsInvul(true);
    }

    @Override
    public void onExit() {
        super.onExit();
        _effected.setIsInvul(false);
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
