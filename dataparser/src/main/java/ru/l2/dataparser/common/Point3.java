package ru.l2.dataparser.common;

import ru.l2.dataparser.annotations.value.IntValue;

public class Point3 {
    @IntValue(withoutName = true)
    public int x;
    @IntValue(withoutName = true)
    public int y;
    @IntValue(withoutName = true)
    public int z;
}