package ru.l2.gameserver.phantoms.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.l2.gameserver.phantoms.model.Phantom;
import ru.l2.gameserver.ThreadPoolManager;

import java.util.concurrent.Future;

public abstract class AbstractPhantomAction implements Runnable {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractPhantomAction.class);
    protected Phantom actor;

    public abstract long getDelay();

    public Future<?> schedule() {
        return ThreadPoolManager.getInstance().schedule(this, actor.getRndDelay(getDelay()));
    }

    public void setActor(final Phantom phantom) {
        actor = phantom;
    }

}
