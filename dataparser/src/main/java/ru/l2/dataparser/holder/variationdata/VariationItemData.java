package ru.l2.dataparser.holder.variationdata;

import ru.l2.dataparser.annotations.array.StringArray;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Mangol
 */
public class VariationItemData {
    @StringValue(withoutName = true)
    public String item_group;
    @IntValue(withoutName = true)
    public int id;
    @StringArray
    public String[] item_list = new String[0];
}
