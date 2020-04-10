package ru.l2.dataparser.holder.itemdata.item.ec_cond;

import ru.l2.dataparser.annotations.array.LinkedArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 28.08.12  12:24
 */
@ParseSuper
public class ECCategory extends DefaultEquipCond {
    @LinkedArray(withoutName = true)
    public int[] categories;
}
