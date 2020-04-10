package ru.l2.authserver.network.gamecomm.gs2as;

import ru.l2.authserver.accounts.SessionManager;
import ru.l2.authserver.network.gamecomm.ReceivablePacket;
import ru.l2.authserver.network.gamecomm.as2gs.PlayerAuthResponse;
import ru.l2.authserver.network.l2.SessionKey;

public class PlayerAuthRequest extends ReceivablePacket {
    private String account;
    private int playOkId1;
    private int playOkId2;
    private int loginOkId1;
    private int loginOkId2;

    @Override
    protected void readImpl() {
        account = readS();
        playOkId1 = readD();
        playOkId2 = readD();
        loginOkId1 = readD();
        loginOkId2 = readD();
    }

    @Override
    protected void runImpl() {
        final SessionKey skey = new SessionKey(loginOkId1, loginOkId2, playOkId1, playOkId2);
        final SessionManager.Session session = SessionManager.getInstance().closeSession(skey);
        if (session == null || !session.getAccount().getLogin().equals(account)) {
            sendPacket(new PlayerAuthResponse(account));
            return;
        }
        sendPacket(new PlayerAuthResponse(session, session.getSessionKey().equals(skey), session.getAccount().getLastServer()));
    }
}
