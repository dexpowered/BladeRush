package ru.l2.gameserver.model.reference;

import ru.l2.commons.lang.reference.AbstractHardReference;

public class L2Reference<T> extends AbstractHardReference<T> {
    public L2Reference(final T reference) {
        super(reference);
    }
}
