package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.AuctionDataHolder;

/**
 * @author : Camelion
 * @date : 25.08.12 18:50
 */
public class AuctionDataParser extends AbstractDataParser<AuctionDataHolder> {
    private static final AuctionDataParser ourInstance = new AuctionDataParser();

    private AuctionDataParser() {
        super(AuctionDataHolder.getInstance());
    }

    public static AuctionDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/auctiondata.txt";
    }
}