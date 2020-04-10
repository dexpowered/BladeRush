package ai;

import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.entity.Reflection;
import ru.l2.gameserver.model.instances.NpcInstance;

public class Aenkinel extends Fighter {
    public Aenkinel(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        final NpcInstance actor = getActor();
        if (actor.getNpcId() == 25694 || actor.getNpcId() == 25695) {
            final Reflection ref = actor.getReflection();
            ref.setReenterTime(System.currentTimeMillis());
        }
        if (actor.getNpcId() == 25694) {
            for (int i = 0; i < 4; ++i) {
                actor.getReflection().addSpawnWithoutRespawn(18820, actor.getLoc(), 250);
            }
        } else if (actor.getNpcId() == 25695) {
            for (int i = 0; i < 4; ++i) {
                actor.getReflection().addSpawnWithoutRespawn(18823, actor.getLoc(), 250);
            }
        }
        super.onEvtDead(killer);
    }
}
