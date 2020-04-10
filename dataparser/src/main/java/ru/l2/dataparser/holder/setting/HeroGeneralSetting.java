package ru.l2.dataparser.holder.setting;

import ru.l2.dataparser.annotations.array.StringArray;

/**
 * @author : Camelion
 * @date : 23.08.12 2:23
 * <p/>
 * Содержит в себе информацию о настройках героев
 */
public class HeroGeneralSetting {
    // Список скилов, выдаваемых герою
    @StringArray(canBeNull = false)
    public String[] hero_skill;
}
