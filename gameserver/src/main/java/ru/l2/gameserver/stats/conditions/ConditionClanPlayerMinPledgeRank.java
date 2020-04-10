package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Player.EPledgeRank;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.stats.Env;

public class ConditionClanPlayerMinPledgeRank extends Condition {
    private final EPledgeRank _minPledgeRank;

    public ConditionClanPlayerMinPledgeRank(final String minPledgeRankName) {
        this(parsePledgeRank(minPledgeRankName));
    }

    public ConditionClanPlayerMinPledgeRank(final EPledgeRank minPledgeRank) {
        _minPledgeRank = minPledgeRank;
    }

    private static EPledgeRank parsePledgeRank(final String pledgeRankText) {
        final EPledgeRank pledgeRank = EPledgeRank.valueOf(pledgeRankText.toUpperCase());
        if (pledgeRank == null) {
            throw new IllegalArgumentException("Unknown pledge rank \"" + pledgeRankText + "\"");
        }
        return pledgeRank;
    }

    @Override
    protected boolean testImpl(final Env env) {
        if (env.character == null) {
            return false;
        }
        final Player player = env.character.getPlayer();
        if (player == null) {
            return false;
        }
        final Clan clan = player.getClan();
        return clan != null && player.getPledgeRank().getRankId() >= _minPledgeRank.getRankId();
    }
}
