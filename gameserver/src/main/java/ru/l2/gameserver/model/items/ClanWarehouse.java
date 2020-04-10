package ru.l2.gameserver.model.items;

import ru.l2.gameserver.model.items.ItemInstance.ItemLocation;
import ru.l2.gameserver.model.pledge.Clan;

public final class ClanWarehouse extends Warehouse {
    public ClanWarehouse(final Clan clan) {
        super(clan.getClanId());
    }

    @Override
    public ItemLocation getItemLocation() {
        return ItemLocation.CLANWH;
    }
}
