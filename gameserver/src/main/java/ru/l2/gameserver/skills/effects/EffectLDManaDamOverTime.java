package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.stats.Env;

public class EffectLDManaDamOverTime extends Effect {
    public EffectLDManaDamOverTime(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean onActionTime() {
        if (_effected.isDead()) {
            return false;
        }
        double manaDam = calc();
        manaDam *= _effected.getLevel() / 2.4;
        if (manaDam > _effected.getCurrentMp() && getSkill().isToggle()) {
            _effected.sendPacket(Msg.NOT_ENOUGH_MP);
            _effected.sendPacket(new SystemMessage(749).addSkillName(getSkill().getId(), getSkill().getDisplayLevel()));
            return false;
        }
        _effected.reduceCurrentMp(manaDam, null);
        return true;
    }
}
