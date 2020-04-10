package ru.l2.gameserver.network.lineage2.clientpackets;

public class GMSnoopEnd extends L2GameClientPacket {
    private int _snoopID;

    @Override
    protected void readImpl() {
        _snoopID = readD();
    }

    @Override
    protected void runImpl() {
    }
}
