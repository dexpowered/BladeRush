package ru.l2.gameserver.network.lineage2.serverpackets;

public class NormalCamera extends L2GameServerPacket {
    @Override
    protected final void writeImpl() {
        writeC(0xd7);
    }
}
