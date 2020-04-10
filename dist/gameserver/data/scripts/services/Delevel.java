package services;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.base.Experience;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.network.lineage2.serverpackets.NpcHtmlMessage;
import ru.l2.gameserver.scripts.Functions;

public class Delevel extends Functions {
    public void delevel_page() {
        final Player player = getSelf();
        if (player == null) {
            return;
        }
        if (!Config.SERVICES_DELEVEL_SELL_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }
        final NpcHtmlMessage msg = new NpcHtmlMessage(5).setFile("scripts/services/level_change.htm");
        msg.replace("%item_id%", String.valueOf(Config.SERVICES_DELEVEL_SELL_ITEM));
        msg.replace("%item_count%", String.valueOf(Config.SERVICES_DELEVEL_SELL_PRICE));
        player.sendPacket(msg);
    }

    public void delevel() {
        final Player player = getSelf();
        if (player == null) {
            return;
        }
        if (!Config.SERVICES_DELEVEL_SELL_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }
        if (player.getLevel() < 3 || player.getLevel() > player.getMaxExp()) {
            return;
        }
        if (getItemCount(player, Config.SERVICES_DELEVEL_SELL_ITEM) < Config.SERVICES_DELEVEL_SELL_PRICE) {
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }
        removeItem(player, Config.SERVICES_DELEVEL_SELL_ITEM, (long) Config.SERVICES_DELEVEL_SELL_PRICE);
        player.addExpAndSp(Experience.LEVEL[player.getLevel() - 2] - player.getExp(), 0L, false, false);
    }
}
