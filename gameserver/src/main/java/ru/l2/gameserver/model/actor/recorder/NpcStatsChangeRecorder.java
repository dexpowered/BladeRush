package ru.l2.gameserver.model.actor.recorder;

import ru.l2.gameserver.model.instances.NpcInstance;

public class NpcStatsChangeRecorder extends CharStatsChangeRecorder<NpcInstance> {
    public NpcStatsChangeRecorder(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onSendChanges() {
        super.onSendChanges();
        if ((_changes & 0x1) == 0x1) {
            _activeChar.broadcastCharInfo();
        }
    }
}
