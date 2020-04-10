package ru.l2.gameserver.handler.npcdialog;


import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;

import java.util.List;

/**
 * @author VISTALL
 * @date 15:32 13.08.11
 */
public interface INpcDialogAppender {
    String getAppend(Player player, NpcInstance npc, int val);

    List<Integer> getNpcIds();
}
