package handler.items;

import ru.l2.gameserver.data.xml.holder.EnchantItemHolder;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.ChooseInventoryItem;

public class EnchantScrolls extends ScriptItemHandler {
    private static int[] ITEM_IDS;

    @Override
    public boolean useItem(final Playable playable, final ItemInstance item, final boolean ctrl) {
        if (playable == null || !playable.isPlayer()) {
            return false;
        }
        final Player player = (Player) playable;
        if (player.getEnchantScroll() != null) {
            return false;
        }
        player.setEnchantScroll(item);
        player.sendPacket(new ChooseInventoryItem(item.getItemId()));
        return true;
    }

    @Override
    public final int[] getItemIds() {
        if (ITEM_IDS == null) {
            ITEM_IDS = EnchantItemHolder.getInstance().getScrollIds();
        }
        return ITEM_IDS;
    }
}
