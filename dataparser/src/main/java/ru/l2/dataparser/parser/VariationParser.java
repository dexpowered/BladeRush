package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.VariationDataHolder;

/**
 * @author : Mangol
 */
public class VariationParser extends AbstractDataParser<VariationDataHolder> {
    private static final VariationParser ourInstance = new VariationParser();

    private VariationParser() {
        super(VariationDataHolder.getInstance());
    }

    public static VariationParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/variationdata.txt";
    }
}
