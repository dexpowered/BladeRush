package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.base.Race;
import ru.l2.gameserver.stats.Env;

public class ConditionTargetPlayerRace extends Condition {
    private final Race _race;

    public ConditionTargetPlayerRace(final String race) {
        _race = Race.valueOf(race.toLowerCase());
    }

    @Override
    protected boolean testImpl(final Env env) {
        final Creature target = env.target;
        return target != null && target.isPlayer() && _race == ((Player) target).getRace();
    }
}
