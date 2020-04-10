package ru.l2.commons.net.io;

import java.net.InetSocketAddress;

public interface IAcceptFilter {
    boolean accept(final InetSocketAddress p0);
}