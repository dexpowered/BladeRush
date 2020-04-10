package ru.l2.gameserver.network.lineage2.clientpackets;

public class RequestPrivateStoreList extends L2GameClientPacket {
    private int unk;

    @Override
    protected void readImpl() {
        unk = readD();
    }

    @Override
    protected void runImpl() {
    }
}
