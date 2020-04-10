package ru.l2.dataparser.holder.manordata;

import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author KilRoy
 */
public class RewardInfo {
    @IntValue(withoutName = true)
    public int reward_id;
    @StringValue(withoutName = true, withoutBounds = true)
    public String reward_item_name;
}