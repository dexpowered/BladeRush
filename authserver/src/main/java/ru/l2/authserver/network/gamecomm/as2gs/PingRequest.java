package ru.l2.authserver.network.gamecomm.as2gs;

import ru.l2.authserver.network.gamecomm.SendablePacket;

public class PingRequest extends SendablePacket {
    @Override
    protected void writeImpl() {
        writeC(0xff);
    }
}
