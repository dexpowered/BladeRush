package ru.l2.gameserver.listener.actor.npc;

import ru.l2.gameserver.listener.NpcListener;
import ru.l2.gameserver.model.instances.NpcInstance;

public interface OnSpawnListener extends NpcListener {
    void onSpawn(final NpcInstance p0);
}
