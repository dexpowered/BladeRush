package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.network.lineage2.serverpackets.PledgeReceiveWarList;

public class RequestPledgeWarList extends L2GameClientPacket {
    private int _type;
    private int _page;

    @Override
    protected void readImpl() {
        _page = readD();
        _type = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Clan clan = activeChar.getClan();
        if (clan != null) {
            activeChar.sendPacket(new PledgeReceiveWarList(clan, _type, _page));
        }
    }
}
