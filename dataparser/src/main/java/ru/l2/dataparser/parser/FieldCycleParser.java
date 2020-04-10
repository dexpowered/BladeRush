package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.FieldCycleHolder;

/**
 * @author : Camelion
 * @date : 27.08.12 2:19
 */
public class FieldCycleParser extends AbstractDataParser<FieldCycleHolder> {
    private static final FieldCycleParser ourInstance = new FieldCycleParser();

    private FieldCycleParser() {
        super(FieldCycleHolder.getInstance());
    }

    public static FieldCycleParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/fieldcycle.txt";
    }
}