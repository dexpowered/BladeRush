package ru.l2.gameserver.data.xml.parser;

import org.jdom2.Element;
import ru.l2.commons.data.xml.AbstractFileParser;
import ru.l2.commons.geometry.Polygon;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.manager.MapRegionManager;
import ru.l2.gameserver.model.Territory;
import ru.l2.gameserver.templates.mapregion.DomainArea;

import java.io.File;

public class DomainParser extends AbstractFileParser<MapRegionManager> {

    protected DomainParser() {
        super(MapRegionManager.getInstance());
    }

    public static DomainParser getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/xml/mapregion/domains.xml");
    }

    @Override
    protected void readData(final MapRegionManager holder, final Element rootElement) {
        for (Element listElement : rootElement.getChildren()) {
            if ("domain".equals(listElement.getName())) {
                final int id = Integer.parseInt(listElement.getAttributeValue("id"));
                Territory territory = null;
                for (Element n : listElement.getChildren()) {
                    if ("polygon".equalsIgnoreCase(n.getName())) {
                        final Polygon shape = ZoneParser.parsePolygon(n);
                        if (!shape.validate()) {
                            error("DomainParser: invalid territory data : " + shape + "!");
                        }
                        if (territory == null) {
                            territory = new Territory();
                        }
                        territory.add(shape);
                    }
                }
                if (territory == null) {
                    throw new RuntimeException("DomainParser: empty territory!");
                }
                holder.addRegionData(new DomainArea(id, territory));
            }
        }
    }

    private static class LazyHolder {
        protected static final DomainParser INSTANCE = new DomainParser();
    }
}
