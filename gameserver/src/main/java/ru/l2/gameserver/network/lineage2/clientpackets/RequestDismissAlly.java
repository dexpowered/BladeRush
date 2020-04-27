package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.pledge.Alliance;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.tables.ClanTable;

public class RequestDismissAlly extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Clan clan = activeChar.getClan();
        if (clan == null) {
            activeChar.sendActionFailed();
            return;
        }
        final Alliance alliance = clan.getAlliance();
        if (alliance == null) {
            activeChar.sendPacket(Msg.YOU_ARE_NOT_CURRENTLY_ALLIED_WITH_ANY_CLANS);
            return;
        }
        if (!activeChar.isAllyLeader()) {
            activeChar.sendPacket(Msg.FEATURE_AVAILABLE_TO_ALLIANCE_LEADERS_ONLY);
            return;
        }
        if (alliance.getMembersCount() > 1) {
            activeChar.sendPacket(Msg.YOU_HAVE_FAILED_TO_DISSOLVE_THE_ALLIANCE);
            return;
        }
        ClanTable.getInstance().dissolveAlly(activeChar);
    }
}
