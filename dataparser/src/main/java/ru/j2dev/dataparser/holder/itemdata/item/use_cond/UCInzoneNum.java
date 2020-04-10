package ru.j2dev.dataparser.holder.itemdata.item.use_cond;

import ru.j2dev.dataparser.annotations.array.IntArray;
import ru.j2dev.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 28.08.12  4:57
 */
@ParseSuper
public class UCInzoneNum extends DefaultUseCond {
    @IntArray(withoutName = true)
    public int[] zone_ids;
}