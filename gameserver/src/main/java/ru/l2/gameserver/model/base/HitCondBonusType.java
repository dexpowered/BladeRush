package ru.l2.gameserver.model.base;

public enum HitCondBonusType {

    AHEAD, SIDE, BACK, HIGH, LOW, DARK, RAIN;

    public static final HitCondBonusType[] VALUES;

    static {
        VALUES = values();
    }
}
