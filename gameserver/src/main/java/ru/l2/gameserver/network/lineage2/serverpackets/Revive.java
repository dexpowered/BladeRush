package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.GameObject;

public class Revive extends L2GameServerPacket {
    private final int _objectId;

    public Revive(final GameObject obj) {
        _objectId = obj.getObjectId();
    }

    @Override
    protected final void writeImpl() {
        writeC(0x7);
        writeD(_objectId);
    }
}
