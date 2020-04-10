package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.GameTimeController;
import ru.l2.gameserver.stats.Env;

public class ConditionGameTime extends Condition {
    private final CheckGameTime _check;
    private final boolean _required;

    public ConditionGameTime(final CheckGameTime check, final boolean required) {
        _check = check;
        _required = required;
    }

    @Override
    protected boolean testImpl(final Env env) {
        switch (_check) {
            case NIGHT: {
                return GameTimeController.getInstance().isNowNight() == _required;
            }
            default: {
                return !_required;
            }
        }
    }

    public enum CheckGameTime {
        NIGHT
    }
}
