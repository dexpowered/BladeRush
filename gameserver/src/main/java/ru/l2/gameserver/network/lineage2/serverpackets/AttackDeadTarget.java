package ru.l2.gameserver.network.lineage2.serverpackets;

public class AttackDeadTarget extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeC(0xa);
    }
}
