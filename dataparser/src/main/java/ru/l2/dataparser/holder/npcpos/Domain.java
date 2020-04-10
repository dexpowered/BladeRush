package ru.l2.dataparser.holder.npcpos;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.common.Point4;

/**
 * @author : Camelion
 * @date : 30.08.12 20:12
 */
public class Domain {
    @StringValue(withoutName = true)
    public String name;
    @IntValue
    public int domain_id;
    @ObjectArray(withoutName = true)
    public Point4[] territory;
}
