package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.entity.events.impl.DuelEvent;

public class ExDuelReady extends L2GameServerPacket {
    private final int _duelType;

    public ExDuelReady(final DuelEvent event) {
        _duelType = event.getDuelType();
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x4c);
        writeD(_duelType);
    }
}
