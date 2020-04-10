package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.stats.Env;

public class EffectMute extends Effect {
    public EffectMute(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!_effected.startMuted()) {
            final Skill castingSkill = _effected.getCastingSkill();
            if (castingSkill != null && castingSkill.isMagic()) {
                _effected.abortCast(true, true);
            }
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }

    @Override
    public void onExit() {
        super.onExit();
        _effected.stopMuted();
    }
}
