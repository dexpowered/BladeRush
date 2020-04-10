package npc.model;

import bosses.AntharasManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public final class HeartOfWardingInstance extends NpcInstance {
    public HeartOfWardingInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(final Player player, final String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }
        if ("enter_lair".equalsIgnoreCase(command)) {
            AntharasManager.enterTheLair(player);
            return;
        }
        super.onBypassFeedback(player, command);
    }
}
