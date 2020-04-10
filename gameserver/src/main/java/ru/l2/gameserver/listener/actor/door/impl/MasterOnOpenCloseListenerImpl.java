package ru.l2.gameserver.listener.actor.door.impl;

import ru.l2.gameserver.listener.actor.door.OnOpenCloseListener;
import ru.l2.gameserver.model.instances.DoorInstance;

public class MasterOnOpenCloseListenerImpl implements OnOpenCloseListener {
    private final DoorInstance _door;

    public MasterOnOpenCloseListenerImpl(final DoorInstance door) {
        _door = door;
    }

    @Override
    public void onOpen(final DoorInstance doorInstance) {
        _door.openMe();
    }

    @Override
    public void onClose(final DoorInstance doorInstance) {
        _door.closeMe();
    }
}
