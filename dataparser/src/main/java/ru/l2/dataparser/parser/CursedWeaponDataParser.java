package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.CursedWeaponDataHolder;

/**
 * @author : Camelion
 * @date : 26.08.12 21:35
 */
public class CursedWeaponDataParser extends AbstractDataParser<CursedWeaponDataHolder> {
    private static final CursedWeaponDataParser ourInstance = new CursedWeaponDataParser();

    private CursedWeaponDataParser() {
        super(CursedWeaponDataHolder.getInstance());
    }

    public static CursedWeaponDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/cursedweapondata.txt";
    }
}