package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

import java.util.List;

public class DeluLizardmanSpecialCommander extends Fighter {
    private boolean _shouted;

    public DeluLizardmanSpecialCommander(final NpcInstance actor) {
        super(actor);
        _shouted = false;
    }

    @Override
    protected void onEvtSpawn() {
        _shouted = false;
        super.onEvtSpawn();
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (Rnd.chance(40) && !_shouted) {
            _shouted = true;
            Functions.npcSay(actor, "Come on my fellows, assist me here!");
            final List<NpcInstance> around = actor.getAroundNpc(1000, 300);
            if (around != null && !around.isEmpty()) {
                for (final NpcInstance npc : around) {
                    if (npc.isMonster()) {
                        npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 5000);
                    }
                }
            }
        }
        super.onEvtAttacked(attacker, damage);
    }
}
