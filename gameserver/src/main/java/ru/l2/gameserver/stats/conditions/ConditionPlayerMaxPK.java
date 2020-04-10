package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerMaxPK extends Condition {
    private final int _pk;

    public ConditionPlayerMaxPK(final int pk) {
        _pk = pk;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.isPlayer() && ((Player) env.character).getPkKills() <= _pk;
    }
}
