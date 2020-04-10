package ru.l2.gameserver.listener.actor.door;

import ru.l2.gameserver.listener.CharListener;
import ru.l2.gameserver.model.instances.DoorInstance;

public interface OnOpenCloseListener extends CharListener {
    void onOpen(final DoorInstance p0);

    void onClose(final DoorInstance p0);
}
