package ru.l2.gameserver.listener.event;

import ru.l2.gameserver.listener.EventListener;
import ru.l2.gameserver.model.entity.events.GlobalEvent;

public interface OnStartStopListener extends EventListener {
    void onStart(final GlobalEvent p0);

    void onStop(final GlobalEvent p0);
}
