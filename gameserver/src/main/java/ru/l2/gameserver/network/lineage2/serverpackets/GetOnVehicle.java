package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.boat.Boat;
import ru.l2.gameserver.utils.Location;

public class GetOnVehicle extends L2GameServerPacket {
    private final int _playerObjectId;
    private final int _boatObjectId;
    private final Location _loc;

    public GetOnVehicle(final Player activeChar, final Boat boat, final Location loc) {
        _loc = loc;
        _playerObjectId = activeChar.getObjectId();
        _boatObjectId = boat.getObjectId();
    }

    @Override
    protected final void writeImpl() {
        writeC(0x5c);
        writeD(_playerObjectId);
        writeD(_boatObjectId);
        writeD(_loc.x);
        writeD(_loc.y);
        writeD(_loc.z);
    }
}
