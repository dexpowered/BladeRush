package ru.l2.gameserver.listener.actor.npc;

import ru.l2.gameserver.listener.NpcListener;
import ru.l2.gameserver.model.instances.NpcInstance;

public interface OnDecayListener extends NpcListener {
    void onDecay(final NpcInstance p0);
}
