package handler.items;

import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.ShowMiniMap;

public class WorldMap extends ScriptItemHandler {
    private static final int[] _itemIds = {1665, 1863};

    @Override
    public boolean useItem(final Playable playable, final ItemInstance item, final boolean ctrl) {
        if (playable == null || !playable.isPlayer()) {
            return false;
        }
        final Player player = (Player) playable;
        player.sendPacket(new ShowMiniMap(player, item.getItemId()));
        return true;
    }

    @Override
    public final int[] getItemIds() {
        return _itemIds;
    }
}
