package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.SettingHolder;

/**
 * @author : Camelion
 * @date : 22.08.12 1:34
 */
public class SettingParser extends AbstractDataParser<SettingHolder> {
    private static final SettingParser ourInstance = new SettingParser();

    private SettingParser() {
        super(SettingHolder.getInstance());
    }

    public static SettingParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/setting.txt";
    }
}