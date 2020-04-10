package ru.l2.gameserver.listener.actor.npc;


import ru.l2.gameserver.listener.NpcListener;
import ru.l2.gameserver.model.instances.NpcInstance;

/**
 * @author PaInKiLlEr
 */
public interface OnShowChatListener extends NpcListener {

    void onShowChat(NpcInstance actor);
}
