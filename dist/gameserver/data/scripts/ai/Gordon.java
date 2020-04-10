package ai;

import ai.moveroute.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;

public class Gordon extends Fighter {
    public Gordon(final NpcInstance actor) {
        super(actor);
    }

    @Override
    public boolean isGlobalAI() {
        return false;
    }

    @Override
    public boolean checkAggression(final Creature target) {
        return target.isPlayable() && target.isCursedWeaponEquipped() && super.checkAggression(target);
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
