package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;

public class RequestHandOverPartyMaster extends L2GameClientPacket {
    private String _name;

    @Override
    protected void readImpl() {
        _name = readS(16);
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Party party = activeChar.getParty();
        if (party == null || !activeChar.getParty().isLeader(activeChar)) {
            activeChar.sendActionFailed();
            return;
        }
        final Player member = party.getPlayerByName(_name);
        if (member == activeChar) {
            activeChar.sendPacket(Msg.YOU_CANNOT_TRANSFER_RIGHTS_TO_YOURSELF);
            return;
        }
        if (member == null) {
            activeChar.sendPacket(Msg.YOU_CAN_TRANSFER_RIGHTS_ONLY_TO_ANOTHER_PARTY_MEMBER);
            return;
        }
        activeChar.getParty().changePartyLeader(member);
    }
}
