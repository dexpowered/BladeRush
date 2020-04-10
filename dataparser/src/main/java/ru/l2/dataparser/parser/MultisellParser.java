package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.MultisellHolder;

/**
 * @author : Camelion
 * @date : 30.08.12 14:23
 */
public class MultisellParser extends AbstractDataParser<MultisellHolder> {
    private static final MultisellParser ourInstance = new MultisellParser();

    private MultisellParser() {
        super(MultisellHolder.getInstance());
    }

    public static MultisellParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/multisell.txt";
    }
}