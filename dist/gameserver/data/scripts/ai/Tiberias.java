package ai;

import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

public class Tiberias extends Fighter {
    public Tiberias(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        final NpcInstance actor = getActor();
        Functions.npcShoutCustomMessage(actor, "scripts.ai.Tiberias.kill");
        super.onEvtDead(killer);
    }
}
