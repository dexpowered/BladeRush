package ru.l2.dataparser.holder.doordata;

import ru.l2.dataparser.annotations.array.IntArray;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Camelion
 * @date : 26.08.12 22:31
 */
@ParseSuper
public class SignBoard extends DefaultSignBoard {
    @IntValue
    public int editor_id;
    @StringValue
    public String html; // Есть не у всех
    @StringValue
    public String texture_name;// Есть не у всех
    @IntArray
    public int[] map_pos; // Есть не у всех
}
