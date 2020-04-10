package ru.l2.authserver.network.gamecomm.gs2as;

import ru.l2.authserver.network.gamecomm.GameServer;
import ru.l2.authserver.network.gamecomm.ReceivablePacket;

public class OnlineStatus extends ReceivablePacket {
    private boolean _online;

    @Override
    protected void readImpl() {
        _online = (readC() == 1);
    }

    @Override
    protected void runImpl() {
        final GameServer gameServer = getGameServer();
        if (!gameServer.isAuthed()) {
            return;
        }
        gameServer.setOnline(_online);
    }
}
