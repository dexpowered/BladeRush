package ru.l2.dataparser.holder.multisell;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.array.StringArray;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.ItemName_Count;

/**
 * @author : Camelion
 * @date : 30.08.12 14:24
 */
public class Multisell {
    @StringValue(withoutName = true)
    public String multisell_name;
    @IntValue(withoutName = true)
    public int multisell_id;
    @StringArray(name = "required_npc")
    public String[] required_npcs = new String[0];
    @IntValue
    public int is_dutyfree;
    @IntValue
    public int is_show_all = 1;
    @IntValue
    public int keep_enchanted;
    @IntValue
    public int show_variation_item;
    @ObjectArray
    public SellInfo[] selllist;

    public static class SellInfo {
        @ObjectArray(withoutName = true)
        public ItemName_Count[] product_infos;
        @ObjectArray(withoutName = true)
        public ItemName_Count[] ingredient_infos;
    }
}
