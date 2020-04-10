package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;

public interface OnAttackListener extends CharListener {
    void onAttack(final Creature attacker, final Creature target);
}
