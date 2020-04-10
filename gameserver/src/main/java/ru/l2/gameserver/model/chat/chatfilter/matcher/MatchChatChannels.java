package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

import java.util.Arrays;

public class MatchChatChannels implements ChatFilterMatcher {
    private final ChatType[] _channels;

    public MatchChatChannels(final ChatType[] channels) {
        _channels = channels;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return Arrays.stream(_channels).anyMatch(ct -> ct == type);
    }
}
