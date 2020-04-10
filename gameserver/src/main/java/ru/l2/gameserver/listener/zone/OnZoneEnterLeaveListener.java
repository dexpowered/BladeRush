package ru.l2.gameserver.listener.zone;

import ru.l2.commons.listener.Listener;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Zone;

public interface OnZoneEnterLeaveListener extends Listener<Zone> {
    void onZoneEnter(final Zone p0, final Creature p1);

    void onZoneLeave(final Zone p0, final Creature p1);
}
