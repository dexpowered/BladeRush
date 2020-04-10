package ru.l2.gameserver.network.lineage2.clientpackets;

public class RequestCreatePledge extends L2GameClientPacket {
    private String _pledgename;

    @Override
    protected void readImpl() {
        _pledgename = readS(64);
    }

    @Override
    protected void runImpl() {
    }
}
