package ru.l2.dataparser.holder.fishingdata;

import ru.l2.dataparser.annotations.array.IntArray;
import ru.l2.dataparser.annotations.value.DoubleValue;

/**
 * @author : Camelion
 * @date : 27.08.12 3:22
 */
public class FishingMonster {
    @IntArray
    public int[] user_level;
    @DoubleValue
    public double monster_probability;
    // Устанавливается через фабрику объектов
    public String[] fishingmonsters;
}
