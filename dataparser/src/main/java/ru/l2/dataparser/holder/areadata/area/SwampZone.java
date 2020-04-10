package ru.l2.dataparser.holder.areadata.area;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 25.08.12  2:32
 * <p/>
 * Зона, чаще всего, замедляющая скорость передвижения персонажа
 */
@ParseSuper
public class SwampZone extends DefaultArea {
    // Активно не во всех Swamp областях. Для некоторых используются default_setting значения.
    @IntValue
    private int move_bonus; // +- к скорости передвижения в этой зоне

    public SwampZone(DefaultArea defaultSetting) {
        super(defaultSetting);
        move_bonus = ((SwampZone) defaultSetting).move_bonus;
    }

    public SwampZone() {

    }
}
