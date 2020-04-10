package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.xml.holder.BoatHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.boat.Boat;
import ru.l2.gameserver.utils.Location;

public class GetOffVehicle extends L2GameClientPacket {
    private final Location _location;
    private int _objectId;

    public GetOffVehicle() {
        _location = new Location();
    }

    @Override
    protected void readImpl() {
        _objectId = readD();
        _location.x = readD();
        _location.y = readD();
        _location.z = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Boat boat = BoatHolder.getInstance().getBoat(_objectId);
        if (boat == null || boat.isMoving()) {
            player.sendActionFailed();
            return;
        }
        boat.oustPlayer(player, _location, false);
    }
}
