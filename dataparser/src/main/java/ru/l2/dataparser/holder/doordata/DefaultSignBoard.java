package ru.l2.dataparser.holder.doordata;

import ru.l2.dataparser.annotations.value.ObjectValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.Point3;

/**
 * @author : Camelion
 * @date : 26.08.12 22:31
 */
public class DefaultSignBoard {
    @StringValue(withoutName = true)
    public String signBoardName; // Название, есть у всех
    @ObjectValue
    public Point3 pos; // Позиция, есть у всех
}
