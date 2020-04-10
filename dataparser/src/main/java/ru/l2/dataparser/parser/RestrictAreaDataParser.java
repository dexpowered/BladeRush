package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.RestrictAreaDataHolder;

/**
 * @author KilRoy
 */
public class RestrictAreaDataParser extends AbstractDataParser<RestrictAreaDataHolder> {
    private static final RestrictAreaDataParser ourInstance = new RestrictAreaDataParser();

    private RestrictAreaDataParser() {
        super(RestrictAreaDataHolder.getInstance());
    }

    public static RestrictAreaDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/RestrictAreaData.txt";
    }
}