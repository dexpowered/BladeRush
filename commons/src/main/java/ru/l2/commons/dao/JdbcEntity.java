package ru.l2.commons.dao;

import java.io.Serializable;

public interface JdbcEntity extends Serializable {
    JdbcEntityState getJdbcState();

    void setJdbcState(final JdbcEntityState p0);

    void save();

    void update();

    void delete();
}
