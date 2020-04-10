package ru.j2dev.gameserver.listener.actor.npc;

import ru.j2dev.gameserver.listener.NpcListener;
import ru.j2dev.gameserver.model.instances.NpcInstance;

public interface OnSpawnListener extends NpcListener {
    void onSpawn(final NpcInstance p0);
}