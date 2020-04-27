package ai;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.ai.Mystic;
import ru.custom.geoengine.GeoEngine;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

public class PaganGuard extends Mystic {
    public PaganGuard(final NpcInstance actor) {
        super(actor);
        actor.startImmobilized();
    }

    @Override
    protected boolean canSeeInSilentMove(final Playable target) {
        return !target.isSilentMoving() || getActor().getParameter("canSeeInSilentMove", true);
    }

    @Override
    public boolean checkAggression(final Creature target) {
        final NpcInstance actor = getActor();
        if (actor.isDead()) {
            return false;
        }
        if (getIntention() != CtrlIntention.AI_INTENTION_ACTIVE || !isGlobalAggro()) {
            return false;
        }
        if (target.isAlikeDead() || !target.isPlayable()) {
            return false;
        }
        if (!target.isInRangeZ(actor.getSpawnedLoc(), (long) actor.getAggroRange())) {
            return false;
        }
        if (target.isPlayable() && !canSeeInSilentMove((Playable) target)) {
            return false;
        }
        if (actor.getNpcId() == 18343 && (Functions.getItemCount((Playable) target, 8067) != 0L || Functions.getItemCount((Playable) target, 8064) != 0L)) {
            return false;
        }
        if (!GeoEngine.canSeeTarget(actor, target, false)) {
            return false;
        }
        if (getIntention() != CtrlIntention.AI_INTENTION_ATTACK) {
            actor.getAggroList().addDamageHate(target, 0, 1);
            setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
        }
        return true;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
