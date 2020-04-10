package ru.l2.gameserver.data.xml.parser;

import org.jdom2.Element;
import ru.l2.commons.data.xml.AbstractFileParser;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.xml.holder.FStringHolder;
import ru.l2.gameserver.templates.FStringTemplate;
import ru.l2.gameserver.templates.StatsSet;

import java.io.File;

/**
 * @author PaInKiLlEr
 */
public final class FStringParser extends AbstractFileParser<FStringHolder> {

    private FStringParser() {
        super(FStringHolder.getInstance());
    }

    public static FStringParser getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/xml/fstring/fstring.xml");
    }

    @Override
    protected void readData(final FStringHolder holder, final Element rootElement) {
        rootElement.getChildren("fstring").forEach(element -> {
            final int id = Integer.parseInt(element.getAttributeValue("id"));
            final String en = element.getAttributeValue("en");
            final String ru = element.getAttributeValue("ru");
            final StatsSet set = new StatsSet();
            set.set("id", id);
            set.set("en", en.replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&amp;", "&"));
            set.set("ru", ru.replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&amp;", "&"));
            final FStringTemplate template = new FStringTemplate(set);
            holder.addTemplate(template);
        });
    }

    private static class LazyHolder {
        protected static final FStringParser INSTANCE = new FStringParser();
    }
}
