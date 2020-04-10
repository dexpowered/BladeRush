package ru.l2.dataparser.holder.manordata;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author KilRoy
 */
public class ProcureInfo {
    @StringValue(withoutName = true, withoutBounds = true)
    public String procure_name;
    @ObjectArray(withoutName = true)
    public RewardInfo[] procure_reward;
}