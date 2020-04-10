package ru.l2.dataparser.holder.itemdata.item.use_cond;

import ru.l2.dataparser.annotations.array.IntArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 28.08.12  4:52
 */
@ParseSuper
public class UCRace extends DefaultUseCond {
    @IntArray(withoutName = true)
    public int[] races;
}
