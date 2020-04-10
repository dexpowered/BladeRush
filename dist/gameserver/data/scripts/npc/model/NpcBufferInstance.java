package npc.model;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.MerchantInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import services.Buffer;

public class NpcBufferInstance extends MerchantInstance {
    public NpcBufferInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... replace) {
        if (val == 0) {
            Buffer.showPage(player, "npc-" + getNpcId(), this);
            return;
        }
        super.showChatWindow(player, val, replace);
    }
}
