package ru.l2.gameserver.network.lineage2.serverpackets;

public class JoinPledge extends L2GameServerPacket {
    private final int _pledgeId;

    public JoinPledge(final int pledgeId) {
        _pledgeId = pledgeId;
    }

    @Override
    protected final void writeImpl() {
        writeC(0x33);
        writeD(_pledgeId);
    }
}
