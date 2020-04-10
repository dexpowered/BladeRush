package ai.residences.clanhall;

import ai.residences.SiegeGuardMystic;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.Zone;
import ru.l2.gameserver.model.entity.events.impl.ClanHallSiegeEvent;
import ru.l2.gameserver.model.entity.events.objects.SpawnExObject;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.scripts.Functions;
import ru.l2.gameserver.tables.SkillTable;
import ru.l2.gameserver.utils.PositionUtils;
import ru.l2.gameserver.utils.ReflectionUtils;

public class GiselleVonHellmann extends SiegeGuardMystic {
    private static final Skill DAMAGE_SKILL = SkillTable.getInstance().getInfo(5003, 1);
    private static final Zone ZONE_1 = ReflectionUtils.getZone("lidia_zone1");
    private static final Zone ZONE_2 = ReflectionUtils.getZone("lidia_zone2");

    public GiselleVonHellmann(final NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        super.onEvtSpawn();
        GiselleVonHellmann.ZONE_1.setActive(true);
        GiselleVonHellmann.ZONE_2.setActive(true);
        Functions.npcShoutCustomMessage(getActor(), "clanhall.GiselleVonHellmann.ARISE_MY_FAITHFUL_SERVANTS_YOU_MY_PEOPLE_WHO_HAVE_INHERITED_THE_BLOOD");
    }

    @Override
    public void onEvtDead(final Creature killer) {
        final NpcInstance actor = getActor();
        super.onEvtDead(killer);
        GiselleVonHellmann.ZONE_1.setActive(false);
        GiselleVonHellmann.ZONE_2.setActive(false);
        Functions.npcShoutCustomMessage(actor, "clanhall.GiselleVonHellmann.AARGH_IF_I_DIE_THEN_THE_MAGIC_FORCE_FIELD_OF_BLOOD_WILL");
        final ClanHallSiegeEvent siegeEvent = actor.getEvent(ClanHallSiegeEvent.class);
        if (siegeEvent == null) {
            return;
        }
        final SpawnExObject spawnExObject = siegeEvent.getFirstObject("boss");
        final NpcInstance lidiaNpc = spawnExObject.getFirstSpawned();
        if (lidiaNpc.getCurrentHpRatio() == 1.0) {
            lidiaNpc.setCurrentHp((double) (lidiaNpc.getMaxHp() / 2), true);
        }
    }

    @Override
    public void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        super.onEvtAttacked(attacker, damage);
        if (PositionUtils.calculateDistance(attacker, actor, false) > 300.0 && Rnd.chance(0.13)) {
            addTaskCast(attacker, GiselleVonHellmann.DAMAGE_SKILL);
        }
    }
}
