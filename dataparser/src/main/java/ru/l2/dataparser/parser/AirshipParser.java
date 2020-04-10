package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.AirshipHolder;

/**
 * @author : Camelion
 * @date : 24.08.12 12:23
 */
public class AirshipParser extends AbstractDataParser<AirshipHolder> {
    private static final AirshipParser ourInstance = new AirshipParser();

    private AirshipParser() {
        super(AirshipHolder.getInstance());
    }

    public static AirshipParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/airship.txt";
    }
}