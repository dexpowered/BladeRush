package ru.l2.gameserver.network.lineage2.serverpackets;

public class ExPeriodicItemList extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeEx(0x87);
        writeD(0);
    }
}
