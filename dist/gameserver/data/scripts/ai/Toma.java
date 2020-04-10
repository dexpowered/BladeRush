package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.ai.DefaultAI;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.MagicSkillUse;
import ru.l2.gameserver.utils.Location;

public class Toma extends DefaultAI {
    private static final long TELEPORT_PERIOD = 1800000L;

    private final Location[] _points;
    private long _lastTeleport;

    public Toma(final NpcInstance actor) {
        super(actor);
        _points = new Location[]{new Location(151680, -174891, -1807, 41400), new Location(154153, -220105, -3402), new Location(178834, -184336, -352)};
        _lastTeleport = System.currentTimeMillis();
    }

    @Override
    protected boolean thinkActive() {
        if (System.currentTimeMillis() - _lastTeleport < Toma.TELEPORT_PERIOD) {
            return false;
        }
        final NpcInstance _thisActor = getActor();
        final Location loc = _points[Rnd.get(_points.length)];
        if (_thisActor.getLoc().equals(loc)) {
            return false;
        }
        _thisActor.broadcastPacketToOthers(new MagicSkillUse(_thisActor, _thisActor, 4671, 1, 1000, 0L));
        ThreadPoolManager.getInstance().schedule(new Teleport(loc), 1000L);
        _lastTeleport = System.currentTimeMillis();
        return true;
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }
}
