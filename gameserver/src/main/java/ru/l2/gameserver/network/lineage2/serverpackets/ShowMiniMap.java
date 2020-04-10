package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.SevenSigns;

public class ShowMiniMap extends L2GameServerPacket {
    private final int _mapId;
    private final int _period;

    public ShowMiniMap(final Player player, final int mapId) {
        _mapId = mapId;
        _period = SevenSigns.getInstance().getCurrentPeriod();
    }

    @Override
    protected final void writeImpl() {
        writeC(0x9d);
        writeD(_mapId);
        writeC(_period);
    }
}
