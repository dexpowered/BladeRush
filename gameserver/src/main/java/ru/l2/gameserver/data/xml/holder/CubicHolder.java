package ru.l2.gameserver.data.xml.holder;

import ru.l2.commons.collections.IntMap;
import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.gameserver.templates.CubicTemplate;

public final class CubicHolder extends AbstractHolder {

    private final IntMap<CubicTemplate> _cubics = new IntMap<>(10);

    public static CubicHolder getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void addCubicTemplate(final CubicTemplate template) {
        _cubics.put(hash(template.getId(), template.getLevel()), template);
    }

    public CubicTemplate getTemplate(final int id, final int level) {
        return _cubics.get(hash(id, level));
    }

    public int hash(final int id, final int level) {
        return id * 10000 + level;
    }

    @Override
    public int size() {
        return _cubics.size();
    }

    @Override
    public void clear() {
        _cubics.clear();
    }

    private static class LazyHolder {
        private static final CubicHolder INSTANCE = new CubicHolder();
    }
}
