package ru.l2.dataparser.holder.areadata.area;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.common.Point3;

/**
 * @author : Camelion
 * @date : 25.08.12  14:19
 */
@ParseSuper
public class TeleportZone extends DefaultArea {
    // Обязательно должно присутствовать для всех зон телепорта
    @ObjectArray(canBeNull = false)
    private Point3[] teleport_points; // Точки телепорта (если больше одной, то выбирается случайным образом)

    public TeleportZone(DefaultArea defaultSetting) {
        super(defaultSetting);
        teleport_points = ((TeleportZone) defaultSetting).teleport_points;
    }

    public TeleportZone() {

    }
}
