package npc.model;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import services.pawnshop.PawnShop;

public class PawnShopInstance extends NpcInstance {
    public PawnShopInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... replace) {
        if (val == 0 && Config.PAWNSHOP_ENABLED) {
            PawnShop.showStartPage(player, this);
            return;
        }
        super.showChatWindow(player, val, replace);
    }
}
