package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;

public class SetPrivateStoreSellMsg extends L2GameClientPacket {
    private String _storename;

    @Override
    protected void readImpl() {
        _storename = readS(32);
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        activeChar.setSellStoreName(_storename);
    }
}
