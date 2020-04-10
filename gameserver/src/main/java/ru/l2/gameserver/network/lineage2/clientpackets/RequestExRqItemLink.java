package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.cache.ItemInfoCache;
import ru.l2.gameserver.model.items.ItemInfo;
import ru.l2.gameserver.network.lineage2.serverpackets.ActionFail;
import ru.l2.gameserver.network.lineage2.serverpackets.ExRpItemLink;

public class RequestExRqItemLink extends L2GameClientPacket {
    private int _objectId;

    @Override
    protected void readImpl() {
        _objectId = readD();
    }

    @Override
    protected void runImpl() {
        final ItemInfo item;
        if ((item = ItemInfoCache.getInstance().get(_objectId)) == null) {
            sendPacket(ActionFail.STATIC);
        } else {
            sendPacket(new ExRpItemLink(item));
        }
    }
}
