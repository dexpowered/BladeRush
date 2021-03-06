package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.model.pledge.UnitMember;
import ru.l2.gameserver.network.lineage2.serverpackets.PledgeReceivePowerInfo;

public class RequestPledgeMemberPowerInfo extends L2GameClientPacket {
    private int _not_known;
    private String _target;

    @Override
    protected void readImpl() {
        _not_known = readD();
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
                activeChar.sendPacket(new PledgeReceivePowerInfo(cm));
            }
        }
    }
}
