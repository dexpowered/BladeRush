package ru.l2.gameserver.model.items.listeners;

import ru.l2.gameserver.listener.inventory.OnEquipListener;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;

public final class AccessoryListener implements OnEquipListener {
    private static final AccessoryListener _instance = new AccessoryListener();

    public static AccessoryListener getInstance() {
        return _instance;
    }

    @Override
    public void onUnequip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        final Player player = (Player) actor;

        if (item.isAccessory()) {
            player.sendUserInfo(true);
        } else {
            player.broadcastCharInfo();
        }
    }

    @Override
    public void onEquip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        final Player player = (Player) actor;
        if (item.isAccessory() || item.getTemplate().isTalisman()) {
            player.sendUserInfo(true);
        } else {
            player.broadcastCharInfo();
        }
    }
}
