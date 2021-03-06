package ru.l2.gameserver.model.items;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance.ItemLocation;

public class PcFreight extends Warehouse {
    public PcFreight(final Player player) {
        super(player.getObjectId());
    }

    public PcFreight(final int objectId) {
        super(objectId);
    }

    @Override
    public ItemLocation getItemLocation() {
        return ItemLocation.FREIGHT;
    }
}
