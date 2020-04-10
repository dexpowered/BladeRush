package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerClassId extends Condition {
    private final int[] _classIds;

    public ConditionPlayerClassId(final String[] ids) {
        _classIds = new int[ids.length];
        for (int i = 0; i < ids.length; ++i) {
            _classIds[i] = Integer.parseInt(ids[i]);
        }
    }

    @Override
    protected boolean testImpl(final Env env) {
        if (!env.character.isPlayer()) {
            return false;
        }
        final int playerClassId = ((Player) env.character).getActiveClassId();
        for (final int id : _classIds) {
            if (playerClassId == id) {
                return true;
            }
        }
        return false;
    }
}
