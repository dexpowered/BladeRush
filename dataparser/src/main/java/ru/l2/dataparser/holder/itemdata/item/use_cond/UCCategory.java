package ru.l2.dataparser.holder.itemdata.item.use_cond;

import ru.l2.dataparser.annotations.array.LinkedArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 28.08.12  4:51
 */
@ParseSuper
public class UCCategory extends DefaultUseCond {
    @LinkedArray(withoutName = true)
    public int[] categories;
}
