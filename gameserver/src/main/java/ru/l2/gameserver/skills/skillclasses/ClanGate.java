package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class ClanGate extends Skill {
    public ClanGate(final StatsSet set) {
        super(set);
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        if (!activeChar.isPlayer()) {
            return false;
        }
        final Player player = (Player) activeChar;
        final Clan clan = player.getClan();
        if (clan == null || !player.isClanLeader() || clan.getCastle() == 0) {
            player.sendPacket(Msg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            return false;
        }
        final SystemMessage msg = Call.canSummonHere(player);
        if (msg != null) {
            activeChar.sendPacket(msg);
            return false;
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (!activeChar.isPlayer()) {
            return;
        }
        final Player player = (Player) activeChar;
        final Clan clan = player.getClan();
        clan.broadcastToOtherOnlineMembers(Msg.COURT_MAGICIAN__THE_PORTAL_HAS_BEEN_CREATED, player);
        getEffects(activeChar, activeChar, getActivateRate() > 0, true);
    }
}
