package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.matching.MatchingRoom;

public class RequestDismissPartyRoom extends L2GameClientPacket {
    private int _roomId;

    @Override
    protected void readImpl() {
        _roomId = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final MatchingRoom room = player.getMatchingRoom();
        if (room.getId() != _roomId || room.getType() != MatchingRoom.PARTY_MATCHING) {
            return;
        }
        if (room.getLeader() != player) {
            return;
        }
        room.disband();
    }
}
