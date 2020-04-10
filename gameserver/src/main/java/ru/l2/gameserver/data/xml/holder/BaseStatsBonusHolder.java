package ru.l2.gameserver.data.xml.holder;


import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.gameserver.templates.BaseStatsBonus;

/**
 * @author JunkyFunky
 **/
public final class BaseStatsBonusHolder extends AbstractHolder {

    private final TIntObjectMap<BaseStatsBonus> _bonuses = new TIntObjectHashMap<>();

    public static BaseStatsBonusHolder getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void addBaseStatsBonus(int value, BaseStatsBonus bonus) {
        _bonuses.put(value, bonus);
    }

    public BaseStatsBonus getBaseStatsBonus(int value) {
        return _bonuses.get(value);
    }

    @Override
    public int size() {
        return _bonuses.size();
    }

    @Override
    public void clear() {
        _bonuses.clear();
    }

    private static class LazyHolder {
        protected static final BaseStatsBonusHolder INSTANCE = new BaseStatsBonusHolder();
    }
}