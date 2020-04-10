package services;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.network.lineage2.serverpackets.NpcHtmlMessage;
import ru.l2.gameserver.scripts.Functions;

public class PKClean extends Functions {
    public void pkclean_page() {
        final Player player = getSelf();
        if (player == null) {
            return;
        }
        if (!Config.SERVICES_PK_CLEAN_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }
        if (player.getPkKills() == 0) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_no_pk.htm"));
            return;
        }
        final NpcHtmlMessage msg = new NpcHtmlMessage(5).setFile("scripts/services/pk_clean.htm");
        msg.replace("%item_id%", String.valueOf(Config.SERVICES_PK_CLEAN_SELL_ITEM));
        msg.replace("%item_count%", String.valueOf(Config.SERVICES_PK_CLEAN_SELL_PRICE));
        player.sendPacket(msg);
    }

    public void pkclean() {
        final Player player = getSelf();
        if (player == null) {
            return;
        }
        if (!Config.SERVICES_PK_CLEAN_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }
        if (player.getPkKills() == 0) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_no_pk.htm"));
            return;
        }
        if (getItemCount(player, Config.SERVICES_PK_CLEAN_SELL_ITEM) < Config.SERVICES_PK_CLEAN_SELL_PRICE) {
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }
        removeItem(player, Config.SERVICES_PK_CLEAN_SELL_ITEM, Config.SERVICES_PK_CLEAN_SELL_PRICE);
        player.setPkKills(0);
        player.sendUserInfo(true);
    }
}
