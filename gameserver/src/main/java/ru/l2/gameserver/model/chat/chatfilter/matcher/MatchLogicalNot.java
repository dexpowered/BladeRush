package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class MatchLogicalNot implements ChatFilterMatcher {
    private final ChatFilterMatcher _match;

    public MatchLogicalNot(final ChatFilterMatcher match) {
        _match = match;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return !_match.isMatch(player, type, msg, recipient);
    }
}
