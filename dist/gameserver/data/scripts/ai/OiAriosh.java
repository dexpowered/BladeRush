package ai;

import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.data.xml.holder.NpcTemplateHolder;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.SimpleSpawner;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.utils.Location;

public class OiAriosh extends Fighter {
    private static final int MOB = 18556;
    private static final int[] _hps = {80, 60, 40, 30, 20, 10, 5, -5};

    private int _hpCount;

    public OiAriosh(final NpcInstance actor) {
        super(actor);
        _hpCount = 0;
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (!actor.isDead() && actor.getCurrentHpPercents() < OiAriosh._hps[_hpCount]) {
            spawnMob(attacker);
            ++_hpCount;
        }
        super.onEvtAttacked(attacker, damage);
    }

    private void spawnMob(final Creature attacker) {
        final NpcInstance actor = getActor();
        try {
            final SimpleSpawner sp = new SimpleSpawner(NpcTemplateHolder.getInstance().getTemplate(18556));
            sp.setLoc(Location.findPointToStay(actor, 100, 120));
            sp.setReflection(actor.getReflection());
            final NpcInstance npc = sp.doSpawn(true);
            npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        _hpCount = 0;
        super.onEvtDead(killer);
    }
}
