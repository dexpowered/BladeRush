package ru.l2.dataparser.holder.areadata.area;

import ru.l2.dataparser.annotations.array.EnumArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.common.PlayerAction;

/**
 * @author : Camelion
 * @date : 25.08.12  0:31
 */
@ParseSuper
public class PeaceZone extends DefaultArea {
    // активно не во всех peace_zone областях
    @EnumArray
    private PlayerAction[] blocked_actions; // Запрещенные в этой зоне действия

    public PeaceZone() {
    }

    public PeaceZone(DefaultArea defaultSetting) {
        super(defaultSetting);
        blocked_actions = ((PeaceZone) defaultSetting).blocked_actions;
    }
}
