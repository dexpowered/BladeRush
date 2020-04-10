package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.matching.MatchingRoom;

public class RequestExDismissMpccRoom extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final MatchingRoom room = player.getMatchingRoom();
        if (room == null || room.getType() != MatchingRoom.CC_MATCHING) {
            return;
        }
        if (room.getLeader() != player) {
            return;
        }
        room.disband();
    }
}
