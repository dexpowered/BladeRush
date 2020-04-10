package ru.l2.dataparser.holder.skilldata.skillacquire;

import ru.l2.dataparser.annotations.value.LongValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author KilRoy
 */
public class AcquireItemNeeded {
    @StringValue(withoutName = true)
    public String item_name;
    @LongValue(withoutName = true)
    public long count;
}