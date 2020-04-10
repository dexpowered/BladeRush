package ru.l2.gameserver.model.event;

import com.stringer.annotations.HideAccess;
import com.stringer.annotations.StringEncryption;
import ru.l2.commons.listener.ListenerList;
import ru.l2.gameserver.listener.event.OnPvpEventStartStopListener;

/**
 * Created by JunkyFunky
 * on 20.05.2018 14:31
 * group j2dev
 */
@HideAccess
@StringEncryption
public class PvpEventListenerList extends ListenerList<PvpEvent> {
    void onStart(PvpEvent pvpEvent) {
        if (!getListeners().isEmpty()) {
            getListeners().forEach(listener -> ((OnPvpEventStartStopListener) listener).onStartPvpEvent(pvpEvent));
        }
    }

    void onStop(PvpEvent pvpEvent) {
        if (!getListeners().isEmpty()) {
            getListeners().forEach(listener -> ((OnPvpEventStartStopListener) listener).onStopPvpEvent(pvpEvent));
        }
    }
}
