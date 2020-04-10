package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.StaticObjectInstance;

public class ChairSit extends L2GameServerPacket {
    private final int _objectId;
    private final int _staticObjectId;

    public ChairSit(final Player player, final StaticObjectInstance throne) {
        _objectId = player.getObjectId();
        _staticObjectId = throne.getUId();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xe1);
        writeD(_objectId);
        writeD(_staticObjectId);
    }
}
