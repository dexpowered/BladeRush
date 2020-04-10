package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.UserBasicActionHolder;

/**
 * @author KilRoy
 */
public class UserBasicActionParser extends AbstractDataParser<UserBasicActionHolder> {
    private static final UserBasicActionParser ourInstance = new UserBasicActionParser();

    private UserBasicActionParser() {
        super(UserBasicActionHolder.getInstance());
    }

    public static UserBasicActionParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/userbasicaction.txt";
    }
}