package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.utils.Location;

public class GetItem extends L2GameServerPacket {
    private final int _playerId;
    private final int _itemObjId;
    private final Location _loc;

    public GetItem(final ItemInstance item, final int playerId) {
        _itemObjId = item.getObjectId();
        _loc = item.getLoc();
        _playerId = playerId;
    }

    @Override
    protected final void writeImpl() {
        writeC(0xd);
        writeD(_playerId);
        writeD(_itemObjId);
        writeD(_loc.x);
        writeD(_loc.y);
        writeD(_loc.z);
    }
}
