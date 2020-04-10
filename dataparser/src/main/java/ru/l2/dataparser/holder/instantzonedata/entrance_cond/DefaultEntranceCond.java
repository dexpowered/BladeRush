package ru.l2.dataparser.holder.instantzonedata.entrance_cond;

import ru.l2.dataparser.annotations.value.EnumValue;
import ru.l2.dataparser.annotations.value.IntValue;

/**
 * @author : Camelion
 * @date : 27.08.12  15:18
 */
public class DefaultEntranceCond {
    @EnumValue(withoutName = true)
    public EntranceCond cond; // Проверять уровень, или проверять квест

    @IntValue(withoutName = true)
    public int cond_param; // Неизвестно (принимает значения 0 или 1)

    public enum EntranceCond {
        check_level, check_quest
    }
}
