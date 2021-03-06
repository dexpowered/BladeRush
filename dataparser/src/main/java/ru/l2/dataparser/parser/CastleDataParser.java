package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.CastleDataHolder;

/**
 * @author : Camelion
 * @date : 25.08.12 22:54
 */
public class CastleDataParser extends AbstractDataParser<CastleDataHolder> {
    private static final CastleDataParser ourInstance = new CastleDataParser();

    private CastleDataParser() {
        super(CastleDataHolder.getInstance());
    }

    public static CastleDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/castledata.txt";
    }
}