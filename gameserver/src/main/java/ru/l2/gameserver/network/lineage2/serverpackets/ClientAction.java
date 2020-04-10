package ru.l2.gameserver.network.lineage2.serverpackets;

public class ClientAction extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeC(0x8f);
    }
}
