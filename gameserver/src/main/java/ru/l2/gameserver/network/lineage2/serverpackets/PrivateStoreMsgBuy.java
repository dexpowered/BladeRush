package ru.l2.gameserver.network.lineage2.serverpackets;

import org.apache.commons.lang3.StringUtils;
import ru.l2.gameserver.model.Player;

public class PrivateStoreMsgBuy extends L2GameServerPacket {
    private final int _objId;
    private final String _name;

    public PrivateStoreMsgBuy(final Player player) {
        _objId = player.getObjectId();
        _name = StringUtils.defaultString(player.getBuyStoreName());
    }

    @Override
    protected final void writeImpl() {
        writeC(0xb9);
        writeD(_objId);
        writeS(_name);
    }
}
