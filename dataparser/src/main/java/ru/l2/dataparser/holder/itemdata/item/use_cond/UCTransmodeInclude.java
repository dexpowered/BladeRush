package ru.l2.dataparser.holder.itemdata.item.use_cond;

import ru.l2.dataparser.annotations.array.EnumArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

import static ru.l2.dataparser.holder.itemdata.item.use_cond.UCTransmodeExclude.*;

/**
 * @author : Camelion
 * @date : 28.08.12  4:59
 */
@ParseSuper
public class UCTransmodeInclude extends DefaultUseCond {
    @EnumArray(withoutName = true)
    public Mode[] modes;
}
