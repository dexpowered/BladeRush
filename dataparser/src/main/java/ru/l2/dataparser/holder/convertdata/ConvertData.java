package ru.l2.dataparser.holder.convertdata;

import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.pch.LinkerFactory;

/**
 * @author : Camelion
 * @date : 26.08.12 12:46
 */
public class ConvertData {
    @StringValue
    public String input_item; // Что конвртируем
    @StringValue
    public String output_item; // Во что конвертируем

    public int getInput() {
        return LinkerFactory.getInstance().findClearValue(input_item);
    }

    public int getOutput() {
        return LinkerFactory.getInstance().findClearValue(output_item);
    }
}
