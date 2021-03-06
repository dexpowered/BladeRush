package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.FreewayInfoHolder;

/**
 * @author : Camelion
 * @date : 27.08.12 13:14
 */
public class FreewayInfoParser extends AbstractDataParser<FreewayInfoHolder> {
    private static final FreewayInfoParser ourInstance = new FreewayInfoParser();

    private FreewayInfoParser() {
        super(FreewayInfoHolder.getInstance());
    }

    public static FreewayInfoParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/freewayinfo.txt";
    }
}