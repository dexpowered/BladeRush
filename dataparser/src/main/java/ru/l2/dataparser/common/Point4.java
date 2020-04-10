package ru.l2.dataparser.common;

import ru.l2.dataparser.annotations.value.IntValue;

public class Point4 {
    @IntValue(withoutName = true)
    public int x;
    @IntValue(withoutName = true)
    public int y;
    @IntValue(withoutName = true)
    public int zMin;
    @IntValue(withoutName = true)
    public int zMax;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZMin() {
        return zMin;
    }

    public int getZMax() {
        return zMax;
    }
}