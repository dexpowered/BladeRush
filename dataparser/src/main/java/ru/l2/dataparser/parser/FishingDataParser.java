package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.FishingDataHolder;

/**
 * @author : Camelion
 * @date : 27.08.12 2:50
 */
public class FishingDataParser extends AbstractDataParser<FishingDataHolder> {
    private static final FishingDataParser ourInstance = new FishingDataParser();

    private FishingDataParser() {
        super(FishingDataHolder.getInstance());
    }

    public static FishingDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/fishingdata.txt";
    }
}