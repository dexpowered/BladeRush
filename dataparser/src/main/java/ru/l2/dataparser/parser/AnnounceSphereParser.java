package ru.l2.dataparser.parser;

import ru.l2.dataparser.common.AbstractDataParser;
import ru.l2.dataparser.holder.AnnounceSphereHolder;

/**
 * @author : Camelion
 * @date : 24.08.12 21:26
 */
public class AnnounceSphereParser extends AbstractDataParser<AnnounceSphereHolder> {
    private static final AnnounceSphereParser ourInstance = new AnnounceSphereParser();

    private AnnounceSphereParser() {
        super(AnnounceSphereHolder.getInstance());
    }

    public static AnnounceSphereParser getInstance() {
        return ourInstance;
    }

    @Override
    protected String getFileName() {
        return "data/pts_scripts/announce_sphere.txt";
    }
}