package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.utils.Location;

import java.util.HashMap;
import java.util.Map;

public class PartyMemberPosition extends L2GameServerPacket {
    private final Map<Integer, Location> positions;

    public PartyMemberPosition() {
        positions = new HashMap<>();
    }

    public PartyMemberPosition add(final Player actor) {
        positions.put(actor.getObjectId(), actor.getLoc());
        return this;
    }

    public int size() {
        return positions.size();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xa7);
        writeD(positions.size());
        positions.forEach((key, value) -> {
            writeD(key);
            writeD(value.x);
            writeD(value.y);
            writeD(value.z);
        });
    }
}
