package ai.custom;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.scripts.Functions;

public class SSQLilimServantFighter extends Fighter {
    private boolean _attacked;

    public SSQLilimServantFighter(final NpcInstance actor) {
        super(actor);
        _attacked = false;
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        if (Rnd.chance(30) && !_attacked) {
            Functions.npcSay(getActor(), Rnd.chance(50) ? "Those who are afraid should get away and those who are brave should fight!" : "This place once belonged to Lord Shilen.");
            _attacked = true;
        }
        super.onEvtAttacked(attacker, damage);
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        if (Rnd.chance(30)) {
            Functions.npcSay(getActor(), Rnd.chance(50) ? "Why are you getting in our way?" : "Shilen... our Shilen!");
        }
        super.onEvtDead(killer);
    }
}
