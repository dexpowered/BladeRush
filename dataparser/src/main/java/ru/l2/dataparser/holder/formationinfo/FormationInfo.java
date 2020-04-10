package ru.l2.dataparser.holder.formationinfo;

import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.annotations.array.IntArray;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

import java.util.List;

/**
 * @author : Camelion
 * @date : 27.08.12 13:05
 */
public class FormationInfo {
    @IntValue
    public int formation_id;
    @StringValue
    public String formation_name;
    @Element(start = "pos_begin", end = "pos_end")
    public List<FormationPos> poses;

    public static class FormationPos {
        @IntValue
        public int pos_id;
        @IntArray(splitter = ",")
        public int[] pos_var;
    }
}
