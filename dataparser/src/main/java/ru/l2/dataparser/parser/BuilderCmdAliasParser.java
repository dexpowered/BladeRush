package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.BuilderCmdAliasHolder;

/**
 * @author : Camelion
 * @date : 25.08.12 22:47
 */
public class BuilderCmdAliasParser extends AbstractDataParser<BuilderCmdAliasHolder> {
    private static final BuilderCmdAliasParser ourInstance = new BuilderCmdAliasParser();

    private BuilderCmdAliasParser() {
        super(BuilderCmdAliasHolder.getInstance());
    }

    public static BuilderCmdAliasParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/buildercmdalias.txt";
    }
}