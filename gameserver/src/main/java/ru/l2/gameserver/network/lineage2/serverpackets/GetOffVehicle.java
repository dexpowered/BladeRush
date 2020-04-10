package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.boat.Boat;
import ru.l2.gameserver.utils.Location;

public class GetOffVehicle extends L2GameServerPacket {
    private final int _playerObjectId;
    private final int _boatObjectId;
    private final Location _loc;

    public GetOffVehicle(final Player cha, final Boat boat, final Location loc) {
        _playerObjectId = cha.getObjectId();
        _boatObjectId = boat.getObjectId();
        _loc = loc;
    }

    @Override
    protected final void writeImpl() {
        writeC(0x5d);
        writeD(_playerObjectId);
        writeD(_boatObjectId);
        writeD(_loc.x);
        writeD(_loc.y);
        writeD(_loc.z);
    }
}
