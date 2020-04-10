package ru.l2.gameserver.data.xml.holder;

import gnu.trove.map.TIntDoubleMap;
import gnu.trove.map.hash.TIntDoubleHashMap;
import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.gameserver.model.base.HitCondBonusType;

public final class HitCondBonusHolder extends AbstractHolder {

    private final TIntDoubleMap _bonusList = new TIntDoubleHashMap();

    private HitCondBonusHolder() {
    }

    public static HitCondBonusHolder getInstance() {
        return SingletonHolder._instance;
    }

    public void addHitCondBonus(final HitCondBonusType type, final double value) {
        _bonusList.put(type.ordinal(), value);
    }

    public double getHitCondBonus(final HitCondBonusType type) {
        return _bonusList.get(type.ordinal());
    }

    @Override
    public int size() {
        return _bonusList.size();
    }

    @Override
    public void clear() {
        _bonusList.clear();
    }

    private static class SingletonHolder {
        protected static final HitCondBonusHolder _instance = new HitCondBonusHolder();
    }
}
