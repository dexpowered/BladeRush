package ru.l2.dataparser.holder.fishingdata;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.DoubleValue;
import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.holder.fishingdata.FishingDistribution.Distribution;

/**
 * @author : Camelion
 * @date : 27.08.12 3:09
 */
public class Lure {
    @IntValue
    public int lure_id;
    @IntValue
    public int lure_item_id;
    @DoubleValue
    public double revision_number;
    @IntValue
    public int length_bonus;
    @DoubleValue
    public double length_rate_bonus;
    @EnumValue
    public LureType lure_type;
    @ObjectArray
    public Distribution[] fish_group_preference;

    public enum LureType {
        normal_lure,
        night_lure
    }
}
