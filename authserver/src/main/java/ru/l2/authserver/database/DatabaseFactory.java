package ru.l2.authserver.database;

import ru.l2.commons.db.AbstractDataBaseFactory;

public class DatabaseFactory extends AbstractDataBaseFactory {

    public static DatabaseFactory getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    @Override
    public String getConfigFile() {
        return "config/database.ini";
    }

    private static class DataSourceHolder {
        private static final DatabaseFactory INSTANCE = new DatabaseFactory();
    }
}
