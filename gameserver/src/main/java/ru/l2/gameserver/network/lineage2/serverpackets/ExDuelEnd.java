package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.entity.events.impl.DuelEvent;

public class ExDuelEnd extends L2GameServerPacket {
    private final int _duelType;

    public ExDuelEnd(final DuelEvent e) {
        _duelType = e.getDuelType();
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x4e);
        writeD(_duelType);
    }
}
