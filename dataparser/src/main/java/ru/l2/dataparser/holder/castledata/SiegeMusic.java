package ru.l2.dataparser.holder.castledata;

import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Camelion
 * @date : 25.08.12 22:54
 */
public class SiegeMusic {
    @IntValue(withoutName = true)
    public int start_time; // Возможно, время после начала осады(секунд)
    @StringValue(withoutName = true)
    public String sound; // Название музыкального файла
    @IntValue(withoutName = true)
    public int length; // продолжительность музыкального файла(секунд)
}
