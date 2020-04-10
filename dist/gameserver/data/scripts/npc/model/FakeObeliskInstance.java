package npc.model;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class FakeObeliskInstance extends NpcInstance {
    public FakeObeliskInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... arg) {
    }

    @Override
    public void onAction(final Player player, final boolean shift) {
        player.sendActionFailed();
    }
}
