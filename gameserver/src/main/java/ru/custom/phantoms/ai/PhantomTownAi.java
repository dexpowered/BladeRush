package ru.custom.phantoms.ai;

import ru.custom.phantoms.PhantomConfig;
import ru.custom.phantoms.action.MoveToNpcAction;
import ru.custom.phantoms.action.RandomMoveAction;
import ru.custom.phantoms.action.RandomUserAction;
import ru.custom.phantoms.action.SpeakAction;
import ru.custom.phantoms.model.Phantom;
import ru.l2.commons.util.Rnd;

public class PhantomTownAi extends AbstractPhantomAi {
    public PhantomTownAi(final Phantom actor) {
        super(actor);
    }

    @Override
    public void runImpl() {
        if (Rnd.chance(PhantomConfig.randomMoveChance)) {
            actor.doAction(new RandomMoveAction());
        } else if (Rnd.chance(PhantomConfig.moveToNpcChance)) {
            actor.doAction(new MoveToNpcAction());
        } else if (Rnd.chance(PhantomConfig.userActionChance)) {
            actor.doAction(new RandomUserAction());
        } else if (Rnd.chance(PhantomConfig.chatspeakChance)) {
            actor.doAction(new SpeakAction());
        }
    }

    @Override
    public PhantomAiType getType() {
        return PhantomAiType.TOWN;
    }
}
