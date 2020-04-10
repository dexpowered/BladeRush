package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;

public class RecipeShopMsg extends L2GameServerPacket {
    private final int _objectId;
    private final String _storeName;

    public RecipeShopMsg(final Player player) {
        _objectId = player.getObjectId();
        _storeName = player.getManufactureName();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xdb);
        writeD(_objectId);
        writeS(_storeName);
    }
}
