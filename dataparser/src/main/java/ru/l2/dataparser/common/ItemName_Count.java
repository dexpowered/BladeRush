package ru.l2.dataparser.common;


import ru.l2.dataparser.annotations.value.LongValue;
import ru.l2.dataparser.annotations.value.StringValue;

public class ItemName_Count {
    @StringValue(withoutName = true)
    public String itemName; // Название предмета
    @LongValue(withoutName = true)
    public long count; // Количество
}