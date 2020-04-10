package ru.l2.dataparser.holder.monrace;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.ObjectValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.Point3;

/**
 * @author : Camelion
 * @date : 30.08.12 14:05
 */
public class MonRace {
    @IntValue
    public int return_rate;
    @IntValue
    public int residenceid;
    @StringValue
    public String begin_music;
    @StringValue
    public String begin_sound;
    @ObjectArray(name = "race_area")
    public MonArea[] race_areas;

    public static class MonArea {
        @IntValue(withoutName = true)
        public int unknown;
        @ObjectValue(withoutName = true)
        public Point3 point1;
        @ObjectValue(withoutName = true)
        public Point3 point2;
        @ObjectValue(withoutName = true)
        public Point3 point3;
        @ObjectValue(withoutName = true)
        public Point3 point4;
    }
}
