package ru.l2.gameserver.network.authcomm.as2gs;

import ru.l2.gameserver.network.authcomm.AuthServerCommunication;
import ru.l2.gameserver.network.authcomm.ReceivablePacket;
import ru.l2.gameserver.network.authcomm.gs2as.PingResponse;

public class PingRequest extends ReceivablePacket {
    @Override
    public void readImpl() {
    }

    @Override
    protected void runImpl() {
        AuthServerCommunication.getInstance().sendPacket(new PingResponse());
    }
}
