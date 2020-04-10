package npc.model;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.manager.DimensionalRiftManager;
import ru.l2.gameserver.manager.DimensionalRiftManager.DimensionalRiftRoom;
import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.DelusionChamber;
import ru.l2.gameserver.model.entity.Reflection;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

import java.util.Map;

public final class DelustionGatekeeperInstance extends NpcInstance {
    public DelustionGatekeeperInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(final Player player, final String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }
        if (command.startsWith("enterDC")) {
            final int izId = Integer.parseInt(command.substring(8));
            final int type = izId - 120;
            final Map<Integer, DimensionalRiftRoom> rooms = DimensionalRiftManager.getInstance().getRooms(type);
            if (rooms == null) {
                player.sendPacket(Msg.SYSTEM_ERROR);
                return;
            }
            final Reflection r = player.getActiveReflection();
            if (r != null) {
                if (player.canReenterInstance(izId)) {
                    player.teleToLocation(r.getTeleportLoc(), r);
                }
            } else if (player.canEnterInstance(izId)) {
                final Party party = player.getParty();
                if (party != null) {
                    new DelusionChamber(party, type, Rnd.get(1, rooms.size() - 1));
                }
            }
        } else {
            super.onBypassFeedback(player, command);
        }
    }
}
