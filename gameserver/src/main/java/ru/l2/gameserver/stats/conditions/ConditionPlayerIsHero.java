package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerIsHero extends Condition {
    private final boolean _value;

    public ConditionPlayerIsHero(final boolean value) {
        _value = value;
    }

    @Override
    protected boolean testImpl(final Env env) {
        if (env.character == null) {
            return false;
        }
        final Player player = env.character.getPlayer();
        return player != null && player.isHero() == _value;
    }
}
