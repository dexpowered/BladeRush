package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.base.Race;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerRace extends Condition {
    private final Race _race;

    public ConditionPlayerRace(final String race) {
        _race = Race.valueOf(race.toLowerCase());
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.isPlayer() && ((Player) env.character).getRace() == _race;
    }
}
