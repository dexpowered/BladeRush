package ai;

import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;

public class Scarecrow extends Fighter {
    public Scarecrow(final NpcInstance actor) {
        super(actor);
        actor.block();
        actor.setIsInvul(true);
    }

    @Override
    protected void onIntentionAttack(final Creature target) {
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
    }

    @Override
    protected void onEvtAggression(final Creature attacker, final int aggro) {
    }
}
