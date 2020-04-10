package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.TransformHolder;

/**
 * @author : Mangol
 */
public class TransformParser extends AbstractDataParser<TransformHolder> {
    private static final TransformParser ourInstance = new TransformParser();

    private TransformParser() {
        super(TransformHolder.getInstance());
    }

    public static TransformParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/transform.txt";
    }
}