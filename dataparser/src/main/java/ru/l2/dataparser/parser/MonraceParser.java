package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.MonraceHolder;

/**
 * @author : Camelion
 * @date : 30.08.12 14:03
 */
public class MonraceParser extends AbstractDataParser<MonraceHolder> {
    private static final MonraceParser ourInstance = new MonraceParser();

    private MonraceParser() {
        super(MonraceHolder.getInstance());
    }

    public static MonraceParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/monrace.txt";
    }
}