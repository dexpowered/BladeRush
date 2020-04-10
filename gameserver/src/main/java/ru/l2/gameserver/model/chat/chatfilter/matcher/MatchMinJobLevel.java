package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class MatchMinJobLevel implements ChatFilterMatcher {
    private final int _classLevel;

    public MatchMinJobLevel(final int classLevel) {
        _classLevel = classLevel;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return player.getClassId().level() < _classLevel;
    }
}
