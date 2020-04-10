package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.items.ItemInfo;
import ru.l2.gameserver.model.items.ItemInstance;

@Deprecated
public class EquipUpdate extends L2GameServerPacket {
    private final ItemInfo _item;

    public EquipUpdate(final ItemInstance item, final int change) {
        (_item = new ItemInfo(item)).setLastChange(change);
    }

    @Override
    protected final void writeImpl() {
        writeC(0x4b);
        writeD(_item.getLastChange());
        writeD(_item.getObjectId());
        writeD(_item.getEquipSlot());
    }
}
