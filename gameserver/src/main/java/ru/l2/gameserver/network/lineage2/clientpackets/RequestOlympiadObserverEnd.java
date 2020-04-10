package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Player;

public class RequestOlympiadObserverEnd extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (Config.OLY_ENABLED && activeChar.isOlyObserver()) {
            activeChar.leaveOlympiadObserverMode();
        }
    }
}
