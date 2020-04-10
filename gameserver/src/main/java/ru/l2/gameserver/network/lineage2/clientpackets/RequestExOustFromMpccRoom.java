package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.matching.MatchingRoom;

public class RequestExOustFromMpccRoom extends L2GameClientPacket {
    private int _objectId;

    @Override
    protected void readImpl() {
        _objectId = readD();
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
        final Player member = GameObjectsStorage.getPlayer(_objectId);
        if (member == null) {
            return;
        }
        if (member == room.getLeader()) {
            return;
        }
        room.removeMember(member, true);
    }
}
