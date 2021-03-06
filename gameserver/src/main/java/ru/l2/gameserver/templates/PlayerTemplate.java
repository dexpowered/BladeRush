package ru.l2.gameserver.templates;

import ru.l2.gameserver.data.xml.holder.ItemTemplateHolder;
import ru.l2.gameserver.model.base.ClassId;
import ru.l2.gameserver.model.base.Race;
import ru.l2.gameserver.templates.item.ItemTemplate;
import ru.l2.gameserver.utils.Location;

import java.util.ArrayList;
import java.util.List;

public class PlayerTemplate extends CharTemplate {
    public final ClassId classId;
    public final Race race;
    public final String className;
    public final Location spawnLoc;
    public final boolean isMale;
    private final List<ItemTemplate> _items;

    public PlayerTemplate(final StatsSet set) {
        super(set);
        spawnLoc = new Location();
        _items = new ArrayList<>();
        classId = ClassId.VALUES[set.getInteger("classId")];
        race = Race.values()[set.getInteger("raceId")];
        className = set.getString("className");
        spawnLoc.set(new Location(set.getInteger("spawnX"), set.getInteger("spawnY"), set.getInteger("spawnZ")));
        isMale = set.getBool("isMale", true);
    }

    public void addItem(final int itemId) {
        final ItemTemplate item = ItemTemplateHolder.getInstance().getTemplate(itemId);
        if (item != null) {
            _items.add(item);
        }
    }

    public List<ItemTemplate> getItems() {
        return _items;
    }
}
