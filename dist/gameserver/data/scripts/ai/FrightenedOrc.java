package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

public class FrightenedOrc extends Fighter {
    private boolean _sayOnAttack;

    public FrightenedOrc(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtSpawn() {
        _sayOnAttack = true;
        super.onEvtSpawn();
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (attacker != null && Rnd.chance(10) && _sayOnAttack) {
            Functions.npcSay(actor, "Don't kill me! If you show mercy I will pay you 10000 adena!");
            _sayOnAttack = false;
        }
        super.onEvtAttacked(attacker, damage);
    }
}
