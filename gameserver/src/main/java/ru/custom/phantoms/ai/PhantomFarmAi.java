package ru.custom.phantoms.ai;

import ru.custom.phantoms.action.AttackMonsterAction;
import ru.custom.phantoms.action.MoveToMonsterAction;
import ru.custom.phantoms.model.Phantom;

/**
 * Created by JunkyFunky
 * on 20.05.2018 20:10
 * group j2dev
 */
public class PhantomFarmAi extends AbstractPhantomAi {

    public PhantomFarmAi(Phantom actor) {
        super(actor);
    }

    @Override
    public void runImpl() {
        if (actor.getTarget() == null) {
            actor.doAction(new MoveToMonsterAction());
        } else if (actor.getTarget() != null) {
            actor.doAction(new AttackMonsterAction());
        }
    }

    @Override
    public PhantomAiType getType() {
        return PhantomAiType.FARM;
    }
}
