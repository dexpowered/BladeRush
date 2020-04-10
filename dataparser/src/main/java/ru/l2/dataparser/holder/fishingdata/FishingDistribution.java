package ru.l2.dataparser.holder.fishingdata;


import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 27.08.12 2:54
 */
public class FishingDistribution {
    @IntValue
    public int distribution_id;
    @ObjectArray
    public Distribution[] default_distribution;
    @ObjectArray
    public Distribution[] night_fishing_distribution;

    public enum FishGroup {
        easy_wide,
        easy_swift,
        easy_ugly,
        wide,
        swift,
        ugly,
        fish_box,
        hard_wide,
        hard_swift,
        hard_ugly,
        hs_fish
    }

    public static class Distribution {
        @EnumValue(withoutName = true)
        public FishGroup fish_group;
        @IntValue(withoutName = true)
        public int unknown;
    }
}
