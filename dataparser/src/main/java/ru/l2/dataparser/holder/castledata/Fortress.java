package ru.l2.dataparser.holder.castledata;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.array.StringArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.ObjectValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.Point3;

/**
 * @author : Camelion
 * @date : 26.08.12 0:41
 */
@ParseSuper
public class Fortress extends Residence {
    @EnumValue
    public Scale fortress_scale; // Размер форта
    @StringArray
    public String[] fortress_related_castle; // Замки, к которым относится форт
    @ObjectValue
    public FortFlagpole fortress_flagpole; // Точка, куда устанавливает флаг
    @StringValue
    public String fortress_flag; // Название итема - флага. (есть в
    // itemdata.txt)
    @ObjectArray(withoutName = true)
    public Point3[] flag_points; // Точки установки флага

    public enum Scale {
        small,
        large
    }

    public static class FortFlagpole {
        @StringValue(withoutName = true)
        public String fort_flagpole; // всегда=flag_pole. если добавить имя
        // форта(без _fort), то получится имя
        // NPC, соответствующего форту
        @ObjectValue(withoutName = true)
        public Point3 point; // всегда=flag_pole. если добавить имя форта(без
        // _fort), то получится имя NPC,
        // соответствующего форту
        @IntValue(withoutName = true)
        public int id; // Какой-то идентификатор, скорее всего чтоб не могли
        // ставить флаги от других фортов.
    }
}
