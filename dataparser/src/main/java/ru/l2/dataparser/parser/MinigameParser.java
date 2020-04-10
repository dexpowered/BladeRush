package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.MinigameHolder;

/**
 * @author : Camelion
 * @date : 30.08.12 13:32
 */
public class MinigameParser extends AbstractDataParser<MinigameHolder> {
    private static final MinigameParser ourInstance = new MinigameParser();

    private MinigameParser() {
        super(MinigameHolder.getInstance());
    }

    public static MinigameParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/minigame.txt";
    }
}