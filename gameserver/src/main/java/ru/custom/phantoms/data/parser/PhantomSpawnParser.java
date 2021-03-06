package ru.custom.phantoms.data.parser;

import org.jdom2.Element;
import ru.custom.phantoms.ai.PhantomAiType;
import ru.custom.phantoms.data.holder.PhantomSpawnHolder;
import ru.custom.phantoms.template.PhantomSpawnTemplate;
import ru.l2.commons.data.xml.AbstractFileParser;
import ru.l2.commons.geometry.Polygon;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Territory;
import ru.l2.gameserver.templates.item.ItemGrade;

import java.io.File;

public class PhantomSpawnParser extends AbstractFileParser<PhantomSpawnHolder> {
    private static final PhantomSpawnParser instance = new PhantomSpawnParser();

    private PhantomSpawnParser() {
        super(PhantomSpawnHolder.getInstance());
    }

    public static PhantomSpawnParser getInstance() {
        return PhantomSpawnParser.instance;
    }

    @Override
    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/xml/phantoms/spawn.xml");
    }

    @Override
    protected void readData(final PhantomSpawnHolder holder, final Element rootElement) {
        for (final Element element : rootElement.getChildren()) {
            if ("spawn".equals(element.getName())) {
                final PhantomSpawnTemplate template = new PhantomSpawnTemplate();
                template.setType(PhantomAiType.valueOf(element.getAttributeValue("type")));
                template.setCount(Integer.parseInt(element.getAttributeValue("count")));
                template.setItemGradeMin(ItemGrade.valueOf(element.getAttributeValue("gradeMin")));
                template.setItemGradeMax(ItemGrade.valueOf(element.getAttributeValue("gradeMax")));
                final Polygon polygon = new Polygon();
                int zmin = Integer.MAX_VALUE;
                int zmax = Integer.MIN_VALUE;
                for (final Element spawnElement : element.getChildren()) {
                    if ("territory".equals(spawnElement.getName())) {
                        for (final Element territoryElement : spawnElement.getChildren()) {
                            if ("add".equals(territoryElement.getName())) {
                                polygon.add(Integer.parseInt(territoryElement.getAttributeValue("x")), Integer.parseInt(territoryElement.getAttributeValue("y")));
                                zmin = Math.min(zmin, Integer.parseInt(territoryElement.getAttributeValue("zmin")));
                                zmax = Math.max(zmax, Integer.parseInt(territoryElement.getAttributeValue("zmax")));
                            }
                        }
                    }
                }
                polygon.setZmax(zmax);
                polygon.setZmin(zmin);
                template.setTerritory(new Territory().add(polygon));
                holder.addSpawn(template);
            }
        }
    }
}
