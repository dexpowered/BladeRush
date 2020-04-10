package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;

public class RequestExShowPostFriendListForPostBox extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
    }
}
