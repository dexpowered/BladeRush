package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.CategoryDataHolder;

/**
 * @author : Camelion
 * @date : 24.08.12 21:39
 */
public class CategoryDataParser extends AbstractDataParser<CategoryDataHolder> {
    private static final CategoryDataParser ourInstance = new CategoryDataParser();

    private CategoryDataParser() {
        super(CategoryDataHolder.getInstance());
    }

    public static CategoryDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/categorydata.txt";
    }
}