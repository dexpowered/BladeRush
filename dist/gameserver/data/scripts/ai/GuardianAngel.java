package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.DefaultAI;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

public class GuardianAngel extends DefaultAI {
    static final String[] flood = {"Waaaah! Step back from the confounded box! I will take it myself!", "Grr! Who are you and why have you stopped me?", "Grr. I've been hit..."};

    public GuardianAngel(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        final NpcInstance actor = getActor();
        Functions.npcSay(actor, GuardianAngel.flood[Rnd.get(2)]);
        return super.thinkActive();
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        final NpcInstance actor = getActor();
        if (actor != null) {
            Functions.npcSay(actor, GuardianAngel.flood[2]);
        }
        super.onEvtDead(killer);
    }
}
