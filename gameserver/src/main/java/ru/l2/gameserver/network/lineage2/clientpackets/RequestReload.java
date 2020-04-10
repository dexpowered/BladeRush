package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.network.lineage2.GameClient;

public class RequestReload extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final GameClient client = getClient();
        final Player player = client.getActiveChar();
        if (player == null) {
            return;
        }
        final long now = System.currentTimeMillis();
        if (now - client.getLastIncomePacketTimeStamp(RequestReload.class) < Config.RELOAD_PACKET_DELAY) {
            player.sendActionFailed();
            return;
        }
        client.setLastIncomePacketTimeStamp(RequestReload.class, now);
        player.sendUserInfo(true);
        World.showObjectsToPlayer(player);
    }
}
