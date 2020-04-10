package ru.l2.gameserver.listener.actor.npc;


import ru.l2.gameserver.listener.NpcListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;

/**
 * Created by JunkyFunky
 * on 30.05.2016.
 * group j2dev
 */
public interface OnShowChatEventListener extends NpcListener {
    void onShowChatEvent(NpcInstance actor, Player player);
}
