package ru.l2.dataparser.holder.doordata;

import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.ObjectValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.Point3;

/**
 * @author : Camelion
 * @date : 27.08.12 0:57
 */
public class Chair {
    @StringValue(withoutName = true)
    public String name; // название
    @IntValue
    public int editor_id;
    @ObjectValue
    public Point3 pos; // Позиция
}
