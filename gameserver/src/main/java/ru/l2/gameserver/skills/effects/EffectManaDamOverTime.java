package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.stats.Env;

public class EffectManaDamOverTime extends Effect {
    public EffectManaDamOverTime(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean onActionTime() {
        if (_effected.isDead()) {
            return false;
        }
        final double manaDam = calc();
        if (manaDam > _effected.getCurrentMp() && getSkill().isToggle()) {
            _effected.sendPacket(Msg.NOT_ENOUGH_MP);
            _effected.sendPacket(new SystemMessage(749).addSkillName(getSkill().getId(), getSkill().getDisplayLevel()));
            return false;
        }
        _effected.reduceCurrentMp(manaDam, null);
        return true;
    }
}
