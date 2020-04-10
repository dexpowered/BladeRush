package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.MatchingRoomManager;
import ru.l2.gameserver.model.CommandChannel;
import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.matching.CCMatchingRoom;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.network.lineage2.serverpackets.ListPartyWaiting;

public class RequestPartyMatchConfig extends L2GameClientPacket {
    private int _page;
    private int _region;
    private int _allLevels;

    @Override
    protected void readImpl() {
        _page = readD();
        _region = readD();
        _allLevels = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Party party = player.getParty();
        final CommandChannel channel = (party != null) ? party.getCommandChannel() : null;
        if (channel != null && channel.getChannelLeader() == player) {
            if (channel.getMatchingRoom() == null) {
                final CCMatchingRoom room = new CCMatchingRoom(player, 1, player.getLevel(), 50, party.getLootDistribution(), player.getName());
                channel.setMatchingRoom(room);
            }
        } else if (channel != null && !channel.getParties().contains(party)) {
            player.sendPacket(SystemMsg.THE_COMMAND_CHANNEL_AFFILIATED_PARTYS_PARTY_MEMBER_CANNOT_USE_THE_MATCHING_SCREEN);
        } else if (party != null && !party.isLeader(player)) {
            player.sendPacket(SystemMsg.THE_LIST_OF_PARTY_ROOMS_CAN_ONLY_BE_VIEWED_BY_A_PERSON_WHO_IS_NOT_PART_OF_A_PARTY);
        } else {
            MatchingRoomManager.getInstance().addToWaitingList(player);
            player.sendPacket(new ListPartyWaiting(_region, _allLevels == 1, _page, player));
        }
    }
}
