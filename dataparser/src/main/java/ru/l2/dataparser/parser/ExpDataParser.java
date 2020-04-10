package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.ExpDataHolder;

/**
 * @author KilRoy
 */
public class ExpDataParser extends AbstractDataParser<ExpDataHolder> {
    private static final ExpDataParser ourInstance = new ExpDataParser();

    private ExpDataParser() {
        super(ExpDataHolder.getInstance());
    }

    public static ExpDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/expdata.txt";
    }
}