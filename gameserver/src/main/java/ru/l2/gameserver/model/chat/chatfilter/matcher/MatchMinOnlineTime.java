package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class MatchMinOnlineTime implements ChatFilterMatcher {
    private final long _onlineTime;

    public MatchMinOnlineTime(final int onlineTime) {
        _onlineTime = onlineTime * 1000L;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return player.getOnlineTime() < _onlineTime;
    }
}
