package ru.l2.gameserver.listener.actor;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;

public interface OnMagicHitListener extends CharListener {
    void onMagicHit(final Creature p0, final Skill p1, final Creature p2);
}
