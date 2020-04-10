package ru.l2.gameserver.model.items.attachment;

import ru.l2.gameserver.model.Player;

public interface PickableAttachment extends ItemAttachment {
    boolean canPickUp(final Player p0);

    void pickUp(final Player p0);
}
