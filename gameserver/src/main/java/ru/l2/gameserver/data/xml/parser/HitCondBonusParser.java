package ru.l2.gameserver.data.xml.parser;

import org.jdom2.Element;
import ru.l2.commons.data.xml.AbstractFileParser;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.xml.holder.HitCondBonusHolder;
import ru.l2.gameserver.model.base.HitCondBonusType;

import java.io.File;

public final class HitCondBonusParser extends AbstractFileParser<HitCondBonusHolder> {

    private HitCondBonusParser() {
        super(HitCondBonusHolder.getInstance());
    }

    public static HitCondBonusParser getInstance() {
        return SingletonHolder._instance;
    }

    @Override
    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/xml/others/hit_cond_bonus.xml");
    }

    @Override
    protected void readData(final HitCondBonusHolder holder, final Element rootElement) {
        rootElement.getChildren().forEach(element -> {
            final HitCondBonusType type = HitCondBonusType.valueOf(element.getAttributeValue("type"));
            final double value = Double.parseDouble(element.getAttributeValue("value"));
            holder.addHitCondBonus(type, value);
        });
    }

    private static class SingletonHolder {
        protected static final HitCondBonusParser _instance = new HitCondBonusParser();
    }
}
