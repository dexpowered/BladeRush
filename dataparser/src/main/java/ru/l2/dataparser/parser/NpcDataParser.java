package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.NpcDataHolder;

/**
 * @author : Camelion
 * @date : 30.08.12 14:44
 */
public class NpcDataParser extends AbstractDataParser<NpcDataHolder> {
    private static final NpcDataParser ourInstance = new NpcDataParser();

    private NpcDataParser() {
        super(NpcDataHolder.getInstance());
    }

    public static NpcDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/npcdata.txt";
    }
}