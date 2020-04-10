package ru.l2.gameserver.network.authcomm.gs2as;

import ru.l2.gameserver.network.authcomm.SendablePacket;

public class PingResponse extends SendablePacket {
    @Override
    protected void writeImpl() {
        writeC(0xff);
        writeQ(System.currentTimeMillis());
    }
}
