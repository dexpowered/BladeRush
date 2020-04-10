package ru.l2.dataparser.holder.itemdata.item.use_cond;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 28.08.12  4:54
 */
@ParseSuper
public class UCRequiredLevel extends DefaultUseCond {
    @IntValue(withoutName = true)
    public int level;
}
