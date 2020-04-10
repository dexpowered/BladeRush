package ru.l2.gameserver.model.actor.recorder;

import ru.l2.gameserver.model.Summon;

public class SummonStatsChangeRecorder extends CharStatsChangeRecorder<Summon> {
    public SummonStatsChangeRecorder(final Summon actor) {
        super(actor);
    }

    @Override
    protected void onSendChanges() {
        super.onSendChanges();
        if ((_changes & 0x2) == 0x2) {
            _activeChar.sendPetInfo();
        } else if ((_changes & 0x1) == 0x1) {
            _activeChar.broadcastCharInfo();
        }
    }
}
