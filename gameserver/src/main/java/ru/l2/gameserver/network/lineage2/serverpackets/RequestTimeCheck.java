package ru.l2.gameserver.network.lineage2.serverpackets;

public class RequestTimeCheck extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeC(0xc1);
    }
}
