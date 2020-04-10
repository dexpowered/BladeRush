package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.EnchantOptionHolder;

/**
 * @author : Camelion
 * @date : 27.08.12 2:01
 */
public class EnchantOptionParser extends AbstractDataParser<EnchantOptionHolder> {
    private static final EnchantOptionParser ourInstance = new EnchantOptionParser();

    private EnchantOptionParser() {
        super(EnchantOptionHolder.getInstance());
    }

    public static EnchantOptionParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/enchantoption.txt";
    }
}