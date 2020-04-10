package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.data.xml.holder.BuyListHolder.NpcTradeList;
import ru.l2.gameserver.model.items.TradeItem;

import java.util.ArrayList;
import java.util.List;

public final class BuyListSeed extends L2GameServerPacket {
    private final int _manorId;
    private final long _money;
    private List<TradeItem> _list;

    public BuyListSeed(final NpcTradeList list, final int manorId, final long currentMoney) {
        _list = new ArrayList<>();
        _money = currentMoney;
        _manorId = manorId;
        _list = list.getItems();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xe8);
        writeD((int) _money);
        writeD(_manorId);
        writeH(_list.size());
        for (final TradeItem item : _list) {
            writeH(item.getItem().getType1());
            writeD(item.getObjectId());
            writeD(item.getItemId());
            writeD((int) item.getCount());
            writeH(item.getItem().getType2ForPackets());
            writeH(item.getCustomType1());
            writeD((int) item.getOwnersPrice());
        }
    }
}
