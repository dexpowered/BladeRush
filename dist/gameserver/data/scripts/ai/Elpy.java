package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.geodata.GeoEngine;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.utils.Location;

public class Elpy extends Fighter {
    public Elpy(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (attacker != null && Rnd.chance(50)) {
            final Location pos = Location.findPointToStay(actor, 150, 200);
            if (GeoEngine.canMoveToCoord(actor.getX(), actor.getY(), actor.getZ(), pos.x, pos.y, pos.z, actor.getGeoIndex())) {
                actor.setRunning();
                addTaskMove(pos, false);
            }
        }
    }

    @Override
    public boolean checkAggression(final Creature target) {
        return false;
    }

    @Override
    protected void onEvtAggression(final Creature target, final int aggro) {
    }
}
