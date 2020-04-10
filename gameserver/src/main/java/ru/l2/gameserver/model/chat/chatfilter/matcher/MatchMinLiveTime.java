package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class MatchMinLiveTime implements ChatFilterMatcher {
    private final long _createTime;

    public MatchMinLiveTime(final int createTime) {
        _createTime = createTime * 1000L;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return System.currentTimeMillis() - player.getCreateTime() < _createTime;
    }
}
