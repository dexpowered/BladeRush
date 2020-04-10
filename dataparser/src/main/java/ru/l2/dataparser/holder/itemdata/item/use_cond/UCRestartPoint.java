package ru.l2.dataparser.holder.itemdata.item.use_cond;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 28.08.12  5:02
 */
@ParseSuper
public class UCRestartPoint extends DefaultUseCond {
    @IntValue(withoutName = true)
    public int point_id;
}
