package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.InstantZoneDataHolder;

/**
 * @author : Camelion
 * @date : 27.08.12 13:54
 */
public class InstantZoneDataParser extends AbstractDataParser<InstantZoneDataHolder> {
    private static final InstantZoneDataParser ourInstance = new InstantZoneDataParser();

    private InstantZoneDataParser() {
        super(InstantZoneDataHolder.getInstance());
    }

    public static InstantZoneDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/instantzonedata.txt";
    }
}