package ru.l2.gameserver.listener.actor.player;


import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;

/**
 * Created by JunkyFunky
 * on 20.12.2017 10:14
 * group j2dev
 */
@FunctionalInterface
public interface OnPlayerClassChange extends PlayerListener {
    void onPlayerClassChange(Player player, int oldClass, int newClass);
}
