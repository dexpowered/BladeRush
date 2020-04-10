package ai;

import ru.l2.gameserver.ai.DefaultAI;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.Earthquake;
import ru.l2.gameserver.network.lineage2.serverpackets.L2GameServerPacket;

import java.util.List;

public class BaiumNpc extends DefaultAI {
    private static final int BAIUM_EARTHQUAKE_TIMEOUT = 900000;
    private long _wait_timeout;

    public BaiumNpc(final NpcInstance actor) {
        super(actor);
        _wait_timeout = 0L;
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }

    @Override
    protected boolean thinkActive() {
        final NpcInstance actor = getActor();
        if (_wait_timeout < System.currentTimeMillis()) {
            _wait_timeout = System.currentTimeMillis() + BAIUM_EARTHQUAKE_TIMEOUT;
            final L2GameServerPacket eq = new Earthquake(actor.getLoc(), 40, 10);
            final List<Player> chars = World.getAroundPlayers(actor,5000, 10000);
            chars.forEach(character -> character.sendPacket(eq));
        }
        return false;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
