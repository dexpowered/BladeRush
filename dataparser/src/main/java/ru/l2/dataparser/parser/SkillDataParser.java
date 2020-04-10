package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.SkillDataHolder;

/**
 * @author KilRoy
 */
public class SkillDataParser extends AbstractDataParser<SkillDataHolder> {
    private static final SkillDataParser ourInstance = new SkillDataParser();

    private SkillDataParser() {
        super(SkillDataHolder.getInstance());
    }

    public static SkillDataParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/skilldata.txt";
    }
}