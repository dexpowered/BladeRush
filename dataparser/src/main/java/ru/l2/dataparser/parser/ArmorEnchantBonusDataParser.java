package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.ArmorEnchantBonusDataHolder;

/**
 * @author : Camelion
 * @date : 24.08.12 21:39
 */
public class ArmorEnchantBonusDataParser extends AbstractDataParser<ArmorEnchantBonusDataHolder> {
    private static final ArmorEnchantBonusDataParser ourInstance = new ArmorEnchantBonusDataParser();

    private ArmorEnchantBonusDataParser() {
        super(ArmorEnchantBonusDataHolder.getInstance());
    }

    public static ArmorEnchantBonusDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/armorenchantbonusdata.txt";
    }
}