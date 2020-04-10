package ru.l2.gameserver.ai;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.entity.boat.Boat;

public class BoatAI extends CharacterAI {
    public BoatAI(final Creature actor) {
        super(actor);
    }

    @Override
    protected void onEvtArrived() {
        final Boat actor = (Boat) getActor();
        if (actor == null) {
            return;
        }
        actor.onEvtArrived();
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }
}
