package npc.model.residences.castle;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.residence.Castle;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.CastleSiegeInfo;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class CastleMessengerInstance extends NpcInstance {
    public CastleMessengerInstance(final int objectID, final NpcTemplate template) {
        super(objectID, template);
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... arg) {
        final Castle castle = getCastle();
        if (player.isCastleLord(castle.getId())) {
            if (castle.getSiegeEvent().isInProgress()) {
                showChatWindow(player, "residence2/castle/sir_tyron021.htm");
            } else {
                showChatWindow(player, "residence2/castle/sir_tyron007.htm");
            }
        } else if (castle.getSiegeEvent().isInProgress()) {
            showChatWindow(player, "residence2/castle/sir_tyron021.htm");
        } else {
            player.sendPacket(new CastleSiegeInfo(castle, player));
        }
    }

    @Override
    public boolean canInteractWithKarmaPlayer() {
        return true;
    }
}
