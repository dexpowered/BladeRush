package ru.l2.authserver.network.gamecomm.gs2as;

import ru.l2.authserver.network.gamecomm.GameServer;
import ru.l2.authserver.network.gamecomm.ReceivablePacket;

public class PlayerInGame extends ReceivablePacket {
    private String account;

    @Override
    protected void readImpl() {
        account = readS();
    }

    @Override
    protected void runImpl() {
        final GameServer gs = getGameServer();
        if (gs.isAuthed()) {
            gs.addAccount(account);
        }
    }
}
