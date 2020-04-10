package npc.model;

import bosses.SailrenManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.ItemFunctions;

public final class SairlenGatekeeperInstance extends NpcInstance {
    private static final int GAZKH = 8784;

    public SairlenGatekeeperInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(final Player player, final String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }
        if (command.startsWith("request_entrance")) {
            if (player.getLevel() < 75) {
                showChatWindow(player, "default/32109-3.htm");
            } else if (ItemFunctions.getItemCount(player, GAZKH) > 0L) {
                final int check = SailrenManager.canIntoSailrenLair(player);
                switch (check) {
                    case 1:
                    case 2:
                        showChatWindow(player, "default/32109-5.htm");
                        break;
                    case 3:
                        showChatWindow(player, "default/32109-4.htm");
                        break;
                    case 4:
                        showChatWindow(player, "default/32109-1.htm");
                        break;
                    case 0:
                        ItemFunctions.removeItem(player, 8784, 1L, true);
                        SailrenManager.setSailrenSpawnTask();
                        SailrenManager.entryToSailrenLair(player);
                        break;
                }
            } else {
                showChatWindow(player, "default/32109-2.htm");
            }
        } else {
            super.onBypassFeedback(player, command);
        }
    }
}
