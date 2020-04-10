package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.SkillAcquireHolder;

/**
 * @author KilRoy
 */
public class SkillAcquireParser extends AbstractDataParser<SkillAcquireHolder> {
    private static final SkillAcquireParser ourInstance = new SkillAcquireParser();

    protected SkillAcquireParser() {
        super(SkillAcquireHolder.getInstance());
    }

    public static SkillAcquireParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/skillacquire.txt";
    }
}