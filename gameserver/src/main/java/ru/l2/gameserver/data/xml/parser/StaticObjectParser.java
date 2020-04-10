package ru.l2.gameserver.data.xml.parser;

import org.jdom2.Element;
import ru.l2.commons.data.xml.AbstractFileParser;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.xml.holder.StaticObjectHolder;
import ru.l2.gameserver.templates.StaticObjectTemplate;
import ru.l2.gameserver.templates.StatsSet;

import java.io.File;

public final class StaticObjectParser extends AbstractFileParser<StaticObjectHolder> {

    private StaticObjectParser() {
        super(StaticObjectHolder.getInstance());
    }

    public static StaticObjectParser getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/xml/others/staticobjects.xml");
    }

    @Override
    protected void readData(final StaticObjectHolder holder, final Element rootElement) {
        rootElement.getChildren().forEach(staticObjectElement -> {
            final StatsSet set = new StatsSet();
            set.set("uid", staticObjectElement.getAttributeValue("id"));
            set.set("stype", staticObjectElement.getAttributeValue("stype"));
            set.set("path", staticObjectElement.getAttributeValue("path"));
            set.set("map_x", staticObjectElement.getAttributeValue("map_x"));
            set.set("map_y", staticObjectElement.getAttributeValue("map_y"));
            set.set("name", staticObjectElement.getAttributeValue("name"));
            set.set("x", staticObjectElement.getAttributeValue("x"));
            set.set("y", staticObjectElement.getAttributeValue("y"));
            set.set("z", staticObjectElement.getAttributeValue("z"));
            set.set("spawn", staticObjectElement.getAttributeValue("spawn"));
            holder.addTemplate(new StaticObjectTemplate(set));
        });
    }

    private static class LazyHolder {
        protected static final StaticObjectParser INSTANCE = new StaticObjectParser();
    }
}
