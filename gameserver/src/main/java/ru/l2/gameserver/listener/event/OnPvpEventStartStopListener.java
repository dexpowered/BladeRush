package ru.l2.gameserver.listener.event;

import ru.l2.gameserver.listener.PvpEventListener;
import ru.l2.gameserver.model.event.PvpEvent;

/**
 * Created by JunkyFunky
 * on 20.05.2018 13:58
 * group j2dev
 */
public interface OnPvpEventStartStopListener extends PvpEventListener {
    void onStartPvpEvent(PvpEvent pvpEvent);
    void onStopPvpEvent(PvpEvent pvpEvent);
}
