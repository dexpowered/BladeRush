package ru.l2.gameserver.phantoms.data.holder;

import ru.l2.gameserver.phantoms.template.PhantomTemplate;
import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.commons.lang.ArrayUtils;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.templates.item.ItemGrade;

import java.util.*;

public class PhantomHolder extends AbstractHolder {
    private static final PhantomHolder instance = new PhantomHolder();
    private final Map<ItemGrade, List<PhantomTemplate>> phantomTemplates;

    public PhantomHolder() {
        phantomTemplates = new HashMap<>();
        for (final ItemGrade itemGrade : ItemGrade.values()) {
            phantomTemplates.put(itemGrade, new ArrayList<>());
        }
    }

    public static PhantomHolder getInstance() {
        return PhantomHolder.instance;
    }

    public void addPhantomTemplate(final ItemGrade itemGrade, final PhantomTemplate phantom) {
        phantomTemplates.get(itemGrade).add(phantom);
        Config.CNAME_FORBIDDEN_NAMES = ArrayUtils.add(Config.CNAME_FORBIDDEN_NAMES, phantom.getName());
    }

    public Map<ItemGrade, List<PhantomTemplate>> getPhantomTemplateMap() {
        return phantomTemplates;
    }

    public PhantomTemplate getRandomPhantomTemplate(final ItemGrade minItemGrade, final ItemGrade maxItemGrade) {
        final ItemGrade rndItemGrade = ItemGrade.values()[Rnd.get(minItemGrade.ordinal(), maxItemGrade.ordinal())];
        final List<PhantomTemplate> gradeList = phantomTemplates.get(rndItemGrade);
        if (gradeList.size() == 0) {
            warn("Can't find template for grade: " + rndItemGrade);
            return null;
        }
        return gradeList.get(Rnd.get(gradeList.size()));
    }

    public boolean isNameExists(final String name) {
        return phantomTemplates.values().stream().flatMap(Collection::stream).anyMatch(template -> template.getName().equalsIgnoreCase(name));
    }

    @Override
    public int size() {
        return phantomTemplates.size();
    }

    @Override
    public void clear() {
        phantomTemplates.clear();
    }

}
