package ru.l2.commons.net.nio.impl;

public interface IClientFactory<T extends MMOClient> {
    T create(final MMOConnection<T> p0);
}
