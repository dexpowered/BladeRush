package ru.l2.gameserver.listener.reflection;

import ru.l2.commons.listener.Listener;
import ru.l2.gameserver.model.entity.Reflection;

public interface OnReflectionCollapseListener extends Listener<Reflection> {
    void onReflectionCollapse(final Reflection p0);
}
