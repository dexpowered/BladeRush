package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.network.lineage2.serverpackets.SendStatus;

public final class RequestStatus extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        getClient().close(new SendStatus());
    }
}
