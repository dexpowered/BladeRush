package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.stats.Env;

public class EffectCharmOfCourage extends Effect {
    public EffectCharmOfCourage(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (_effected.isPlayer()) {
            _effected.getPlayer().setCharmOfCourage(true);
        }
    }

    @Override
    public void onExit() {
        super.onExit();
        _effected.getPlayer().setCharmOfCourage(false);
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
