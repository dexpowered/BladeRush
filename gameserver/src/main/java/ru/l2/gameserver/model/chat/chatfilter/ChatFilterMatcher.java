package ru.l2.gameserver.model.chat.chatfilter;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public interface ChatFilterMatcher {
    boolean isMatch(final Player p0, final ChatType p1, final String p2, final Player p3);
}
