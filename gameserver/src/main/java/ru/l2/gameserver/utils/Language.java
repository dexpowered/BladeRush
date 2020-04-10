package ru.l2.gameserver.utils;

public enum Language {
    ENGLISH("en"),
    RUSSIAN("ru");

    public static final Language[] VALUES = values();

    private final String _shortName;

    Language(final String shortName) {
        _shortName = shortName;
    }

    public String getShortName() {
        return _shortName;
    }
}
