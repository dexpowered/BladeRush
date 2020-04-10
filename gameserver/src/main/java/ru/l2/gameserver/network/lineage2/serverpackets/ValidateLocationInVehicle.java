package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.utils.Location;

public class ValidateLocationInVehicle extends L2GameServerPacket {
    private final int _playerObjectId;
    private final int _boatObjectId;
    private final Location _loc;

    public ValidateLocationInVehicle(final Player player) {
        _playerObjectId = player.getObjectId();
        _boatObjectId = player.getBoat().getObjectId();
        _loc = player.getInBoatPosition();
    }

    @Override
    protected final void writeImpl() {
        writeC(0x73);
        writeD(_playerObjectId);
        writeD(_boatObjectId);
        writeD(_loc.x);
        writeD(_loc.y);
        writeD(_loc.z);
        writeD(_loc.h);
    }
}
