package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.categorydata.CategoryDefine;

import java.util.List;

/**
 * @author : Camelion
 * @date : 26.08.12 12:11
 */
public class CategoryDataHolder extends AbstractHolder {
    private static final CategoryDataHolder ourInstance = new CategoryDataHolder();
    @Element(start = "category_define_begin", end = "category_define_end")
    private List<CategoryDefine> definedCategories;

    private CategoryDataHolder() {
    }

    public static CategoryDataHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return definedCategories.size();
    }

    public List<CategoryDefine> getDefinedCategories() {
        return definedCategories;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}