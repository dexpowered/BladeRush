package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.templates.npc.NpcTemplate;

public class NpcNotSayInstance extends NpcInstance {
    public NpcNotSayInstance(final int objectID, final NpcTemplate template) {
        super(objectID, template);
        setHasChatWindow(false);
    }
}
