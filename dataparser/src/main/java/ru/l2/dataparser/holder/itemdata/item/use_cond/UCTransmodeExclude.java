package ru.l2.dataparser.holder.itemdata.item.use_cond;

import ru.l2.dataparser.annotations.array.EnumArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 28.08.12  4:46
 */
@ParseSuper
public class UCTransmodeExclude extends DefaultUseCond {
    @EnumArray(withoutName = true)
    public Mode[] modes;


    public enum Mode {
        tt_flying, tt_pure_stat, tt_non_transform
    }
}
