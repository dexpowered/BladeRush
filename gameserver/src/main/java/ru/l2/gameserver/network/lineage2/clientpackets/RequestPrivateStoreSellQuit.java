package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;

public class RequestPrivateStoreSellQuit extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (!activeChar.isInStoreMode() || (activeChar.getPrivateStoreType() != 1 && activeChar.getPrivateStoreType() != 8)) {
            activeChar.sendActionFailed();
            return;
        }
        activeChar.setPrivateStoreType(Player.STORE_PRIVATE_NONE);
        activeChar.standUp();
        activeChar.broadcastCharInfo();
    }
}
