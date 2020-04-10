package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;

public interface OnCurrentHpDamageListener extends CharListener {
    void onCurrentHpDamage(final Creature p0, final double p1, final Creature p2, final Skill p3);
}
