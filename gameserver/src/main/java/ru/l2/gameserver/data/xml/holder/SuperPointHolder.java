package ru.l2.gameserver.data.xml.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.gameserver.templates.npc.superPoint.SuperPoint;

import java.util.HashMap;
import java.util.Map;

public final class SuperPointHolder extends AbstractHolder {

    private final Map<String, SuperPoint> superPointMap = new HashMap<>();

    public static SuperPointHolder getInstance() {
        return SingletonHolder._instance;
    }

    private static class SingletonHolder {
        protected static final SuperPointHolder _instance = new SuperPointHolder();
    }

    public void addSuperPoints(final String pointName, final SuperPoint superPoints) {
        superPointMap.put(pointName, superPoints);
    }

    public SuperPoint getSuperPointsByName(final String pointName) {
        return superPointMap.get(pointName);
    }

    @Override
    public int size() {
        return superPointMap.size();
    }

    @Override
    public void clear() {
        superPointMap.clear();
    }
}