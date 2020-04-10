package ru.custom.phantoms.ai;

import ru.custom.phantoms.model.Phantom;
import ru.l2.gameserver.ai.PlayerAI;

public abstract class AbstractPhantomAi extends PlayerAI {
    protected Phantom actor;

    public AbstractPhantomAi(final Phantom actor) {
        super(actor);
        this.actor = actor;
    }

    @Override
    public abstract void runImpl();

    public abstract PhantomAiType getType();

    @Override
    public Phantom getActor() {
        return (Phantom) super.getActor();
    }
}
