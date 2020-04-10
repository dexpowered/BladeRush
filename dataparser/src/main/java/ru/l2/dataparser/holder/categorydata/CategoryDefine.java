package ru.l2.dataparser.holder.categorydata;

import ru.l2.dataparser.annotations.array.LinkedArray;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Camelion
 * @date : 26.08.12 12:12
 */
public class CategoryDefine {
    @StringValue
    public String name; // Название категории, совпадает с category_pch.txt
    @LinkedArray
    public int[] category;// Список классов, попадающих в категорию ( совпадает
    // с manual_pch.txt)
}
