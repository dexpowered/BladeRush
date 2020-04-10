package ru.l2.dataparser.holder.expdata;

import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.LongValue;

/**
 * @author KilRoy
 */
public class ExpDataTable {
    @IntValue
    private int level;
    @LongValue
    private long exp;

    public int getLevel() {
        return level;
    }

    public long getExp() {
        return exp;
    }
}