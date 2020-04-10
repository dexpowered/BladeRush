package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.templates.npc.NpcTemplate;

@Deprecated
public class NoActionNpcInstance extends NpcInstance {
    public NoActionNpcInstance(final int objectID, final NpcTemplate template) {
        super(objectID, template);
    }

    @Override
    public void onAction(final Player player, final boolean dontMove) {
        player.sendActionFailed();
    }
}
