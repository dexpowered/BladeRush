package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.MatchingRoomManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.matching.MatchingRoom;

public class RequestExJoinMpccRoom extends L2GameClientPacket {
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
        if (player.getMatchingRoom() != null) {
            return;
        }
        final MatchingRoom room = MatchingRoomManager.getInstance().getMatchingRoom(MatchingRoom.CC_MATCHING, _roomId);
        if (room == null) {
            return;
        }
        room.addMember(player);
    }
}
