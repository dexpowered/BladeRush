package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.PCParameterHolder;

public class PCParameterParser extends AbstractDataParser<PCParameterHolder> {
    private static final PCParameterParser ourInstance = new PCParameterParser();

    private PCParameterParser() {
        super(PCParameterHolder.getInstance());
    }

    public static PCParameterParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/pc_parameter.txt";
    }
}