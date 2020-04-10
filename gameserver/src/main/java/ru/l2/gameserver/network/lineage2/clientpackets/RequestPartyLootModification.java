package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;

public class RequestPartyLootModification extends L2GameClientPacket {
    private byte _mode;

    @Override
    protected void readImpl() {
        _mode = (byte) readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (_mode < 0 || _mode > 4) {
            return;
        }
        final Party party = activeChar.getParty();
        if (party == null || _mode == party.getLootDistribution() || party.getPartyLeader() != activeChar) {
            return;
        }
        party.requestLootChange(_mode);
    }
}
