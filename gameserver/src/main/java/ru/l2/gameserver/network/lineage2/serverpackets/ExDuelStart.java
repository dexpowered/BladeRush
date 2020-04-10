package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.entity.events.impl.DuelEvent;

public class ExDuelStart extends L2GameServerPacket {
    private final int _duelType;

    public ExDuelStart(final DuelEvent e) {
        _duelType = e.getDuelType();
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x4d);
        writeD(_duelType);
    }
}
