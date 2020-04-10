package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.network.lineage2.serverpackets.ExSendManorList;

public class RequestManorList extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        sendPacket(new ExSendManorList());
    }
}
