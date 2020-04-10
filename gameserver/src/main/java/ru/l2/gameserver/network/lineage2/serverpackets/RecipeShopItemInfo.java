package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;

public class RecipeShopItemInfo extends L2GameServerPacket {
    private final int _recipeId;
    private final int _shopId;
    private final int _curMp;
    private final int _maxMp;
    private final long _price;
    private int _success;

    public RecipeShopItemInfo(final Player activeChar, final Player manufacturer, final int recipeId, final long price, final int success) {
        _success = -1;
        _recipeId = recipeId;
        _shopId = manufacturer.getObjectId();
        _price = price;
        _success = success;
        _curMp = (int) manufacturer.getCurrentMp();
        _maxMp = manufacturer.getMaxMp();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xda);
        writeD(_shopId);
        writeD(_recipeId);
        writeD(_curMp);
        writeD(_maxMp);
        writeD(_success);
        writeD((int) _price);
    }
}
