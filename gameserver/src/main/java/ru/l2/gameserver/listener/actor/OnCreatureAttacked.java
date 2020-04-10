package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;

/**
 * Created by JunkyFunky
 * on 11.07.2018 20:44
 * group j2dev
 */
public interface OnCreatureAttacked extends CharListener {

    void onCreatureAttacked(final Creature attacker, final Creature target);
}
