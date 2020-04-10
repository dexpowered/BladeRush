package ru.l2.dataparser.holder.itemdata.item.ec_cond;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 28.08.12  12:34
 */
@ParseSuper
public class ECSubjob extends DefaultEquipCond {
    @IntValue(withoutName = true)
    public int subjob;
}
