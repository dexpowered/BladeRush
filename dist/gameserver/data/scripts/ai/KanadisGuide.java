package ai;

import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;

import java.util.List;

public class KanadisGuide extends Fighter {
    public KanadisGuide(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();
        final NpcInstance actor = getActor();
        final List<NpcInstance> around = actor.getAroundNpc(5000, 300);
        if (around != null && !around.isEmpty()) {
            for (final NpcInstance npc : around) {
                if (npc.getNpcId() == 36562) {
                    actor.getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, npc, 5000);
                }
            }
        }
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (attacker.getNpcId() == 36562) {
            actor.getAggroList().addDamageHate(attacker, 0, 1);
            startRunningTask(2000L);
            setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
        }
        super.onEvtAttacked(attacker, damage);
    }

    @Override
    protected boolean maybeMoveToHome() {
        return false;
    }
}
