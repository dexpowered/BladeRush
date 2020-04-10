package ru.l2.gameserver.tables;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.L2GameServerPacket;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;

import java.util.List;
import java.util.stream.Collectors;

public class GmListTable {
    public static List<Player> getAllGMs() {
        return GameObjectsStorage.getPlayers().stream().filter(Player::isGM).collect(Collectors.toList());
    }

    public static List<Player> getAllVisibleGMs() {
        return GameObjectsStorage.getPlayers().stream().filter(player -> player.isGM() && !player.isInvisible()).collect(Collectors.toList());
    }

    public static void sendListToPlayer(final Player player) {
        final List<Player> gmList = Config.HIDE_GM_STATUS ? getAllVisibleGMs() : getAllGMs();
        if (gmList.isEmpty()) {
            player.sendPacket(Msg.THERE_ARE_NOT_ANY_GMS_THAT_ARE_PROVIDING_CUSTOMER_SERVICE_CURRENTLY);
            return;
        }
        player.sendPacket(Msg._GM_LIST_);
        gmList.stream().map(gm -> new SystemMessage(704).addString(gm.getName())).forEach(player::sendPacket);
    }

    public static void broadcastToGMs(final L2GameServerPacket packet) {
        getAllGMs().forEach(gm -> gm.sendPacket(packet));
    }

    public static void broadcastMessageToGMs(final String message) {
        getAllGMs().forEach(gm -> gm.sendMessage(message));
    }
}
