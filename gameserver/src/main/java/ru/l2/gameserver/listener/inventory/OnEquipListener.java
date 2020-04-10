package ru.l2.gameserver.listener.inventory;

import ru.l2.commons.listener.Listener;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.items.ItemInstance;

public interface OnEquipListener extends Listener<Playable> {
    void onEquip(final int p0, final ItemInstance p1, final Playable p2);

    void onUnequip(final int p0, final ItemInstance p1, final Playable p2);
}
