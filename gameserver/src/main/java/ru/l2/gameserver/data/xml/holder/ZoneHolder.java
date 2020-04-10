package ru.l2.gameserver.data.xml.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.gameserver.templates.ZoneTemplate;

import java.util.HashMap;
import java.util.Map;

public class ZoneHolder extends AbstractHolder {

    private final Map<String, ZoneTemplate> _zones = new HashMap<>();

    public static ZoneHolder getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void addTemplate(final ZoneTemplate zone) {
        _zones.put(zone.getName(), zone);
    }

    public ZoneTemplate getTemplate(final String name) {
        return _zones.get(name);
    }

    public Map<String, ZoneTemplate> getZones() {
        return _zones;
    }

    @Override
    public int size() {
        return _zones.size();
    }

    @Override
    public void clear() {
        _zones.clear();
    }

    private static class LazyHolder {
        private static final ZoneHolder INSTANCE = new ZoneHolder();
    }
}
