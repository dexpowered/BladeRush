package ru.l2.gameserver.model.chat.chatfilter;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class ChatFilter {
    public static final int ACTION_NONE = 0;
    public static final int ACTION_BAN_CHAT = 1;
    public static final int ACTION_WARN_MSG = 2;
    public static final int ACTION_REPLACE_MSG = 3;
    public static final int ACTION_REDIRECT_MSG = 4;
    private final ChatFilterMatcher _matcher;
    private final int _action;
    private final String _value;

    public ChatFilter(final ChatFilterMatcher matcher, final int action, final String value) {
        _matcher = matcher;
        _action = action;
        _value = value;
    }

    public int getAction() {
        return _action;
    }

    public String getValue() {
        return _value;
    }

    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return _matcher.isMatch(player, type, msg, recipient);
    }
}
