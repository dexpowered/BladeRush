package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.data.xml.holder.ItemTemplateHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.PremiumItem;
import ru.l2.gameserver.network.lineage2.serverpackets.ExGetPremiumItemList;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage2;

public final class RequestWithDrawPremiumItem extends L2GameClientPacket {
    private int _itemNum;
    private int _charId;
    private long _itemcount;

    @Override
    protected void readImpl() {
        _itemNum = readD();
        _charId = readD();
        _itemcount = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (_itemcount <= 0L) {
            return;
        }
        if (activeChar.getObjectId() != _charId) {
            return;
        }
        if (activeChar.getPremiumItemList().isEmpty()) {
            return;
        }
        if (activeChar.getWeightPenalty() >= 3 || activeChar.getInventoryLimit() * 0.8 <= activeChar.getInventory().getSize()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_RECEIVE_THE_VITAMIN_ITEM_BECAUSE_YOU_HAVE_EXCEED_YOUR_INVENTORY_WEIGHT_QUANTITY_LIMIT);
            return;
        }
        if (activeChar.isProcessingRequest()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_RECEIVE_A_VITAMIN_ITEM_DURING_AN_EXCHANGE);
            return;
        }
        final PremiumItem _item = activeChar.getPremiumItemList().get(_itemNum);
        if (_item == null) {
            return;
        }
        final boolean stackable = ItemTemplateHolder.getInstance().getTemplate(_item.getItemId()).isStackable();
        if (_item.getCount() < _itemcount) {
            return;
        }
        if (!stackable) {
            for (int i = 0; i < _itemcount; ++i) {
                addItem(activeChar, _item.getItemId(), 1L);
            }
        } else {
            addItem(activeChar, _item.getItemId(), _itemcount);
        }
        if (_itemcount < _item.getCount()) {
            activeChar.getPremiumItemList().get(_itemNum).updateCount(_item.getCount() - _itemcount);
            activeChar.updatePremiumItem(_itemNum, _item.getCount() - _itemcount);
        } else {
            activeChar.getPremiumItemList().remove(_itemNum);
            activeChar.deletePremiumItem(_itemNum);
        }
        if (activeChar.getPremiumItemList().isEmpty()) {
            activeChar.sendPacket(Msg.THERE_ARE_NO_MORE_VITAMIN_ITEMS_TO_BE_FOUND);
        } else {
            activeChar.sendPacket(new ExGetPremiumItemList(activeChar));
        }
    }

    private void addItem(final Player player, final int itemId, final long count) {
        player.getInventory().addItem(itemId, count);
        player.sendPacket(SystemMessage2.obtainItems(itemId, count, 0));
    }
}
