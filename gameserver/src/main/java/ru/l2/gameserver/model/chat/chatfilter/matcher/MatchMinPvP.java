package ru.l2.gameserver.model.chat.chatfilter.matcher;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class MatchMinPvP implements ChatFilterMatcher {
    private final int _pvp;

    public MatchMinPvP(final int pvp) {
        _pvp = pvp;
    }

    @Override
    public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
        return player.getPvpKills() < _pvp;
    }
}
