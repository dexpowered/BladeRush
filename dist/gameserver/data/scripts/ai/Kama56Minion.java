package ai;

import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;

public class Kama56Minion extends Fighter {
    public Kama56Minion(final NpcInstance actor) {
        super(actor);
        actor.setIsInvul(true);
    }

    @Override
    protected void onEvtAggression(final Creature attacker, final int aggro) {
        if (aggro < 10000000) {
            return;
        }
        super.onEvtAggression(attacker, aggro);
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
    }
}
