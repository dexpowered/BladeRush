package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.network.lineage2.serverpackets.FinishRotating;
import ru.l2.gameserver.network.lineage2.serverpackets.StartRotating;
import ru.l2.gameserver.stats.Env;

public final class EffectBluff extends Effect {
    public EffectBluff(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean checkCondition() {
        return (!getEffected().isNpc() || getEffected().isMonster()) && super.checkCondition();
    }

    @Override
    public void onStart() {
        getEffected().broadcastPacket(new StartRotating(getEffected(), getEffected().getHeading(), 1, 65535));
        getEffected().broadcastPacket(new FinishRotating(getEffected(), getEffector().getHeading(), 65535));
        getEffected().setHeading(getEffector().getHeading());
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
