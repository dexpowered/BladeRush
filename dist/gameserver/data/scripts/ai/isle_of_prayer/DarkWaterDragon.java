package ai.isle_of_prayer;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.data.xml.holder.NpcTemplateHolder;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.SimpleSpawner;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.utils.Location;

public class DarkWaterDragon extends Fighter {
    private static final int FAFURION = 18482;
    private static final int SHADE1 = 22268;
    private static final int SHADE2 = 22269;
    private static final int[] MOBS = {22268, 22269};
    private static final int MOBS_COUNT = 5;
    private static final int RED_CRYSTAL = 9596;

    private int _mobsSpawned;

    public DarkWaterDragon(final NpcInstance actor) {
        super(actor);
        _mobsSpawned = 0;
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (!actor.isDead()) {
            switch (_mobsSpawned) {
                case 0: {
                    _mobsSpawned = 1;
                    spawnShades(attacker);
                    break;
                }
                case 1: {
                    if (actor.getCurrentHp() < actor.getMaxHp() / 2) {
                        _mobsSpawned = 2;
                        spawnShades(attacker);
                        break;
                    }
                    break;
                }
            }
        }
        super.onEvtAttacked(attacker, damage);
    }

    private void spawnShades(final Creature attacker) {
        final NpcInstance actor = getActor();
        for (int i = 0; i < 5; ++i) {
            try {
                final SimpleSpawner sp = new SimpleSpawner(NpcTemplateHolder.getInstance().getTemplate(DarkWaterDragon.MOBS[Rnd.get(DarkWaterDragon.MOBS.length)]));
                sp.setLoc(Location.findPointToStay(actor, 100, 120));
                final NpcInstance npc = sp.doSpawn(true);
                npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, Rnd.get(1, 100));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        _mobsSpawned = 0;
        final NpcInstance actor = getActor();
        try {
            final SimpleSpawner sp = new SimpleSpawner(NpcTemplateHolder.getInstance().getTemplate(18482));
            sp.setLoc(Location.findPointToStay(actor, 100, 120));
            sp.doSpawn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (killer != null) {
            final Player player = killer.getPlayer();
            if (player != null && Rnd.chance(77)) {
                actor.dropItem(player, 9596, 1L);
            }
        }
        super.onEvtDead(killer);
    }

    @Override
    protected boolean randomWalk() {
        return _mobsSpawned == 0;
    }
}
