package ru.l2.gameserver.phantoms.action;

import ru.l2.gameserver.phantoms.PhantomConfig;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.network.lineage2.clientpackets.RequestActionUse;

public class RandomUserAction extends AbstractPhantomAction {
    @Override
    public long getDelay() {
        return 0L;
    }

    @Override
    public void run() {
        final int actionId = PhantomConfig.userActions[Rnd.get(PhantomConfig.userActions.length)];
        final RequestActionUse.Action actionOptional = RequestActionUse.Action.find(actionId);
    }
}
