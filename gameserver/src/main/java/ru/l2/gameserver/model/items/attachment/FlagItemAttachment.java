package ru.l2.gameserver.model.items.attachment;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;

public interface FlagItemAttachment extends PickableAttachment {
    void onLogout(final Player p0);

    void onDeath(final Player p0, final Creature p1);

    void onEnterPeace(final Player p0);

    boolean canAttack(final Player p0);

    boolean canCast(final Player p0, final Skill p1);
}
