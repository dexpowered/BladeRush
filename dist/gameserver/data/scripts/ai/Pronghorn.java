package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.SimpleSpawner;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.utils.Location;

import java.util.stream.IntStream;

public class Pronghorn extends Fighter {
    private static final int MOBS = 22087;
    private static final int MOBS_COUNT = 4;
    private boolean _mobsNotSpawned;

    public Pronghorn(final NpcInstance actor) {
        super(actor);
        _mobsNotSpawned = true;
    }

    @Override
    protected void onEvtSeeSpell(final Skill skill, final Creature caster) {
        final NpcInstance actor = getActor();
        if (skill.isMagic()) {
            return;
        }
        if (_mobsNotSpawned) {
            _mobsNotSpawned = false;
            IntStream.range(0, MOBS_COUNT).forEach(i -> {
                try {
                    final SimpleSpawner sp = new SimpleSpawner(MOBS);
                    sp.setLoc(Location.findPointToStay(actor, 100, 120));
                    final NpcInstance npc = sp.doSpawn(true);
                    if (caster.isPet() || caster.isSummon()) {
                        npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, caster, Rnd.get(2, 100));
                    }
                    npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, caster.getPlayer(), Rnd.get(1, 100));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        _mobsNotSpawned = true;
        super.onEvtDead(killer);
    }

    @Override
    protected boolean randomWalk() {
        return _mobsNotSpawned;
    }
}
