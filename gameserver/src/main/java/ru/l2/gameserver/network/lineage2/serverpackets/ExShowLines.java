package ru.l2.gameserver.network.lineage2.serverpackets;

public class ExShowLines extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeEx(0xa5);
    }
}
