package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.CubicDataHolder;

/**
 * @author : Camelion
 * @date : 26.08.12 13:13
 */
public class CubicDataParser extends AbstractDataParser<CubicDataHolder> {
    private static final CubicDataParser ourInstance = new CubicDataParser();

    private CubicDataParser() {
        super(CubicDataHolder.getInstance());
    }

    public static CubicDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/cubicdata.txt";
    }
}