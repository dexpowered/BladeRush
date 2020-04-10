package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.ExMPCCShowPartyMemberInfo;

public class RequestExMPCCShowPartyMembersInfo extends L2GameClientPacket {
    private int _objectId;

    @Override
    protected void readImpl() {
        _objectId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null || !activeChar.isInParty() || !activeChar.getParty().isInCommandChannel()) {
            return;
        }
        for (final Party party : activeChar.getParty().getCommandChannel().getParties()) {
            final Player leader = party.getPartyLeader();
            if (leader != null && leader.getObjectId() == _objectId) {
                activeChar.sendPacket(new ExMPCCShowPartyMemberInfo(party));
                break;
            }
        }
    }
}
