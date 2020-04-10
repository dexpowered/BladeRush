package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;

public interface OnDeathListener extends CharListener {
    void onDeath(Creature actor, Creature killer);
}
