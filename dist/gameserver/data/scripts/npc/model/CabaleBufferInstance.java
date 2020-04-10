package npc.model;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public final class CabaleBufferInstance extends NpcInstance {
    public CabaleBufferInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... arg) {
    }

    @Override
    public void showChatWindow(final Player player, final String filename, final Object... ar) {
    }

    @Override
    public void onBypassFeedback(final Player player, final String command) {
    }
}
