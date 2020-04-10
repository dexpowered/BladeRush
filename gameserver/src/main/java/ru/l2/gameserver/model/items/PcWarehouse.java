package ru.l2.gameserver.model.items;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance.ItemLocation;

public class PcWarehouse extends Warehouse {
    public PcWarehouse(final Player owner) {
        super(owner.getObjectId());
    }

    public PcWarehouse(final int ownerId) {
        super(ownerId);
    }

    @Override
    public ItemLocation getItemLocation() {
        return ItemLocation.WAREHOUSE;
    }
}
