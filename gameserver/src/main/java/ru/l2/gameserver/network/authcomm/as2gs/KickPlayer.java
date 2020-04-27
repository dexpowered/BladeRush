package ru.l2.gameserver.network.authcomm.as2gs;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.authcomm.AuthServerCommunication;
import ru.l2.gameserver.network.authcomm.ReceivablePacket;
import ru.l2.gameserver.network.lineage2.GameClient;
import ru.l2.gameserver.network.lineage2.serverpackets.ServerClose;

public class KickPlayer extends ReceivablePacket {
    String account;

    @Override
    public void readImpl() {
        account = readS();
    }

    @Override
    protected void runImpl() {
        GameClient client = AuthServerCommunication.getInstance().removeWaitingClient(account);
        if (client == null) {
            client = AuthServerCommunication.getInstance().removeAuthedClient(account);
        }
        if (client == null) {
            return;
        }
        final Player activeChar = client.getActiveChar();
        if (activeChar != null) {
            activeChar.sendPacket(Msg.ANOTHER_PERSON_HAS_LOGGED_IN_WITH_THE_SAME_ACCOUNT);
            activeChar.kick();
        } else {
            client.close(ServerClose.STATIC);
        }
    }
}
