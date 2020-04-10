package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInfo;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.model.items.Warehouse.ItemClassComparator;
import ru.l2.gameserver.network.lineage2.clientpackets.RequestPackageSend;

import java.util.LinkedList;
import java.util.List;

public class PackageSendableList extends L2GameServerPacket {
    private final int _targetObjectId;
    private final long _adena;
    private final List<ItemInfo> _itemList;

    public PackageSendableList(final int objectId, final Player cha) {
        _adena = cha.getAdena();
        _targetObjectId = objectId;
        final List<ItemInstance> items = cha.getInventory().getItems();
        items.sort(ItemClassComparator.getInstance());
        _itemList = new LinkedList<>();
        items.stream().filter(RequestPackageSend::CanSendItem).map(ItemInfo::new).forEach(_itemList::add);
    }

    @Override
    protected final void writeImpl() {
        writeC(0xc3);
        writeD(_targetObjectId);
        writeD((int) _adena);
        writeD(_itemList.size());
        _itemList.forEach(item -> {
            writeH(item.getItem().getType1());
            writeD(item.getObjectId());
            writeD(item.getItemId());
            writeD((int) item.getCount());
            writeH(item.getItem().getType2ForPackets());
            writeH(item.getCustomType1());
            writeD(item.getItem().getBodyPart());
            writeH(item.getEnchantLevel());
            writeH(item.getCustomType2());
            writeH(0);
            writeD(item.getObjectId());
        });
    }
}
