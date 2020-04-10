package ru.custom.phantoms.action;

import ru.custom.phantoms.PhantomConfig;
import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.utils.Location;

public class RandomMoveAction extends AbstractPhantomAction {
    @Override
    public long getDelay() {
        return 0L;
    }

    @Override
    public void run() {
        final Location loc = Location.findPointToStay(actor.getSpawnLoc(), PhantomConfig.randomMoveDistance, ReflectionManager.DEFAULT.getGeoIndex());
        actor.moveToLocation(loc, 50, true);
    }
}
