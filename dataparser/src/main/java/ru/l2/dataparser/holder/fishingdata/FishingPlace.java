package ru.l2.dataparser.holder.fishingdata;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.common.Point4;

/**
 * @author : Camelion
 * @date : 27.08.12 3:03
 */
public class FishingPlace {
    @IntValue
    public int fishing_place_id;
    @ObjectArray
    public Point4[] territory;
    @IntValue
    public int limit_grid;
    @IntValue
    public int distribution_id;
    @EnumValue
    public FishingPlaceType fishing_place_type;
    @IntValue
    public int maintain_distribution_time;

    public enum FishingPlaceType {
        fishing_place_default,
        fishing_place_type1,
        fishing_place_type2
    }
}
