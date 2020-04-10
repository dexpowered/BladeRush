package ru.l2.gameserver.phantoms.data.parser;

import org.jdom2.Element;
import ru.l2.gameserver.phantoms.data.holder.PhantomPhraseHolder;
import ru.l2.gameserver.phantoms.template.PhantomPhraseTemplate;
import ru.l2.commons.data.xml.AbstractFileParser;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.network.lineage2.components.ChatType;
import ru.l2.gameserver.utils.Language;

import java.io.File;

public class PhantomPhraseParser extends AbstractFileParser<PhantomPhraseHolder> {
    public static final PhantomPhraseParser instance = new PhantomPhraseParser();

    public PhantomPhraseParser() {
        super(PhantomPhraseHolder.getInstance());
    }

    public static PhantomPhraseParser getInstance() {
        return PhantomPhraseParser.instance;
    }

    @Override
    public File getXMLFile() {
        if (Language.ENGLISH.getShortName().equalsIgnoreCase(Config.DEFAULT_LANG)) {
            return new File(Config.DATAPACK_ROOT, "data/xml/phantoms/phrases_en.xml");
        }
        return new File(Config.DATAPACK_ROOT, "data/xml/phantoms/phrases_ru.xml");
    }


    @Override
    protected void readData(final PhantomPhraseHolder holder, final Element rootElement) {
        rootElement.getChildren().stream().filter(element -> "phrase".equals(element.getName())).forEach(element -> {
            final PhantomPhraseTemplate template = new PhantomPhraseTemplate();
            template.setPhrase(element.getAttributeValue("text"));
            template.setType(ChatType.valueOf(element.getAttributeValue("type")));
            final String chance = element.getAttributeValue("chance");
            if (chance != null) {
                template.setChance(Integer.parseInt(chance));
            }
            holder.addPhrase(template.getType(), template);
        });
    }
}
