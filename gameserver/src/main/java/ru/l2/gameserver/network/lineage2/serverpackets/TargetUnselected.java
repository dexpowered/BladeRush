package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.utils.Location;

public class TargetUnselected extends L2GameServerPacket {
    private final int _targetId;
    private final Location _loc;

    public TargetUnselected(final GameObject obj) {
        _targetId = obj.getObjectId();
        _loc = obj.getLoc();
    }

    @Override
    protected final void writeImpl() {
        writeC(0x2a);
        writeD(_targetId);
        writeD(_loc.x);
        writeD(_loc.y);
        writeD(_loc.z);
    }
}
