package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.utils.Location;

public class ValidateLocation extends L2GameServerPacket {
    private final int _chaObjId;
    private final Location _loc;

    public ValidateLocation(final Creature cha) {
        _chaObjId = cha.getObjectId();
        _loc = cha.getLoc();
    }

    @Override
    protected final void writeImpl() {
        writeC(0x61);
        writeD(_chaObjId);
        writeD(_loc.x);
        writeD(_loc.y);
        writeD(_loc.z);
        writeD(_loc.h);
    }
}
