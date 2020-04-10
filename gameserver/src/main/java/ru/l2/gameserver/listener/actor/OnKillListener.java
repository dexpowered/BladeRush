package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;

public interface OnKillListener extends CharListener {
    void onKill(final Creature p0, final Creature p1);

    boolean ignorePetOrSummon();
}
