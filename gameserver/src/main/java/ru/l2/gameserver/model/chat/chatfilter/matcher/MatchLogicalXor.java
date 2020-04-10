package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class MatchLogicalXor implements ChatFilterMatcher {
    private final ChatFilterMatcher[] _matches;

    public MatchLogicalXor(final ChatFilterMatcher[] matches) {
        _matches = matches;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        boolean match = false;
        for (final ChatFilterMatcher m : _matches) {
            if (m.isMatch(player, type, msg, recipient)) {
                if (match) {
                    return false;
                }
                match = true;
            }
        }
        return match;
    }
}
