package ai.freya;

import bosses.AntharasManager;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;

public class AntharasMinion extends Fighter {
    public AntharasMinion(final NpcInstance actor) {
        super(actor);
        actor.startDebuffImmunity();
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();
        AntharasManager.getZone().getInsidePlayers().forEach(player -> notifyEvent(CtrlEvent.EVT_AGGRESSION, player, 5000));
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        getActor().doCast(getSkillInfo(5097, 1), getActor(), true);
        super.onEvtDead(killer);
    }

    @Override
    protected void returnHome(final boolean clearAggro, final boolean teleport) {
    }
}
