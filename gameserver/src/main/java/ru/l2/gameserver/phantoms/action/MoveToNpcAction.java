package ru.l2.gameserver.phantoms.action;

import ru.l2.gameserver.phantoms.PhantomConfig;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.model.instances.NpcInstance;

import java.util.List;

public class MoveToNpcAction extends AbstractPhantomAction {
    @Override
    public long getDelay() {
        return 0L;
    }

    @Override
    public void run() {
        final List<NpcInstance> npcList = actor.getAroundNpc(PhantomConfig.moveToNpcRange, 200);
        if (npcList.size() == 0) {
            return;
        }
        actor.moveToLocation(Rnd.get(npcList).getLoc(), 100, true);
    }
}
