package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Ranger;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

public class KarulBugbear extends Ranger {
    private boolean _firstTimeAttacked;

    public KarulBugbear(final NpcInstance actor) {
        super(actor);
        _firstTimeAttacked = true;
    }

    @Override
    protected void onEvtSpawn() {
        _firstTimeAttacked = true;
        super.onEvtSpawn();
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (_firstTimeAttacked) {
            _firstTimeAttacked = false;
            if (Rnd.chance(25)) {
                Functions.npcSay(actor, "Your rear is practically unguarded!");
            }
        } else if (Rnd.chance(10)) {
            Functions.npcSay(actor, "Watch your back!");
        }
        super.onEvtAttacked(attacker, damage);
    }
}
