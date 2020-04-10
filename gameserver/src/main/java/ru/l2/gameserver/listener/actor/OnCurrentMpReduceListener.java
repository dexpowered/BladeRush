package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;

public interface OnCurrentMpReduceListener extends CharListener {
    void onCurrentMpReduce(final Creature p0, final double p1, final Creature p2);
}
