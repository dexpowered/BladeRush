package ru.l2.dataparser.holder.variationdata;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.DoubleValue;
import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Mangol
 */
public class VariationData {
    @EnumValue
    public WeaponType weapon_type;
    @StringValue
    public String mineral;
    @ObjectArray
    public VariationGroup[] variation1 = new VariationGroup[0];
    @ObjectArray
    public VariationGroup[] variation2 = new VariationGroup[0];

    public enum WeaponType {
        warrior,
        mage
    }

    public static class VariationGroup {
        @ObjectArray(withoutName = true)
        public VariationInfo[] option;
        @DoubleValue(withoutName = true)
        public double group_chance;
    }

    public static class VariationInfo {
        @StringValue(withoutName = true)
        public String option_name;
        @DoubleValue(withoutName = true)
        public double chance;
    }
}
