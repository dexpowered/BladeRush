package ru.l2.dataparser.holder.areadata.area;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 25.08.12  14:23
 */
@ParseSuper
public class SSQZone extends DefaultArea {
    @IntValue
    private int exp_penalty_per; // штраф к опыту (в процентах)

    @EnumValue
    private OnOffZoneParam item_drop; // дроп вещей вкл/выкл

    public SSQZone(DefaultArea defaultSetting) {
        super(defaultSetting);
        exp_penalty_per = ((SSQZone) defaultSetting).exp_penalty_per;
        item_drop = ((SSQZone) defaultSetting).item_drop;
    }

    public SSQZone() {

    }
}
