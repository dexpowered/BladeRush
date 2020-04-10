package ru.l2.gameserver.network.lineage2.serverpackets;

public class ExSetMpccRouting extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeEx(0x37);
    }
}
