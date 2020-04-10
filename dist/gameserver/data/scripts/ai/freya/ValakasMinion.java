package ai.freya;

import bosses.ValakasManager;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Mystic;
import ru.l2.gameserver.model.instances.NpcInstance;

public class ValakasMinion extends Mystic {
    public ValakasMinion(final NpcInstance actor) {
        super(actor);
        actor.startImmobilized();
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();
        ValakasManager.getZone().getInsidePlayers().forEach(player -> notifyEvent(CtrlEvent.EVT_AGGRESSION, player, 5000));
    }
}
