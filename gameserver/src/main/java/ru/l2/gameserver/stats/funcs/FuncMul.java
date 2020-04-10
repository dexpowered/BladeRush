package ru.l2.gameserver.stats.funcs;

import ru.l2.gameserver.stats.Env;
import ru.l2.gameserver.stats.Stats;

public class FuncMul extends Func {
    public FuncMul(final Stats stat, final int order, final Object owner, final double value) {
        super(stat, order, owner, value);
    }

    @Override
    public void calc(final Env env) {
        env.value *= getValue();
    }
}
