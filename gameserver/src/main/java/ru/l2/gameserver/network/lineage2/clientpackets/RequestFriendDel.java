package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;

public class RequestFriendDel extends L2GameClientPacket {
    private String _name;

    @Override
    protected void readImpl() {
        _name = readS(16);
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        player.getFriendList().removeFriend(_name);
    }
}
