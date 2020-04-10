package ru.l2.gameserver.network.lineage2.serverpackets;

public class ExShowAdventurerGuideBook extends L2GameServerPacket {
    @Override
    protected final void writeImpl() {
        writeEx(0x37);
    }
}
