package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;

public interface OnCreatureAttack extends CharListener {
    void onCreatureAttack(final Creature target, final Creature attacker);
}
