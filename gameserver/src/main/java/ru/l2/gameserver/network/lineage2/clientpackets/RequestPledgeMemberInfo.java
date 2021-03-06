package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.model.pledge.UnitMember;
import ru.l2.gameserver.network.lineage2.serverpackets.PledgeReceiveMemberInfo;

public class RequestPledgeMemberInfo extends L2GameClientPacket {
    private int _pledgeType;
    private String _target;

    @Override
    protected void readImpl() {
        _pledgeType = readD();
        _target = readS(16);
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Clan clan = activeChar.getClan();
        if (clan != null) {
            final UnitMember cm = clan.getAnyMember(_target);
            if (cm != null) {
                activeChar.sendPacket(new PledgeReceiveMemberInfo(cm));
            }
        }
    }
}
