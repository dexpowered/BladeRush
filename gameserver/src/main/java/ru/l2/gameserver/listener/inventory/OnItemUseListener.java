package ru.l2.gameserver.listener.inventory;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

/**
 * Created by JunkyFunky
 * on 12.06.2018 16:44
 * group j2dev
 */
public interface OnItemUseListener extends PlayerListener {
    void onItemUse(int itemId, Player player);
}
