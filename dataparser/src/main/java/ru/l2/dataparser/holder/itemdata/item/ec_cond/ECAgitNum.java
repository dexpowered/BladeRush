package ru.l2.dataparser.holder.itemdata.item.ec_cond;

import ru.l2.dataparser.annotations.array.IntArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 28.08.12  12:44
 */
@ParseSuper
public class ECAgitNum extends DefaultEquipCond {
    @IntArray(withoutName = true)
    public int[] agit_nums;
}
