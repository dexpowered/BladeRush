package ru.l2.dataparser.holder.restrictarea;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.Point4;

/**
 * @author KilRoy
 */
public class RestrictAreaData {
    @StringValue
    private String name; // название области имеющей ограничение

    @ObjectArray
    private Point4[] range; // описание координат области ограничения(x, y, z-min, z-max)

    public String getName() {
        return name;
    }

    public Point4[] getRestrictedPoints() {
        return range;
    }
}