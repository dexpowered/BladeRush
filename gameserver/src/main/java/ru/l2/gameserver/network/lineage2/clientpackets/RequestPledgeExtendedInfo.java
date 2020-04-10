package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;

public class RequestPledgeExtendedInfo extends L2GameClientPacket {
    private String _name;

    @Override
    protected void readImpl() {
        _name = readS();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isGM()) {
            activeChar.sendMessage("RequestPledgeExtendedInfo");
        }
    }
}
