package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;

public class L2FriendStatus extends L2GameServerPacket {
    private final String _charName;
    private final boolean _login;

    public L2FriendStatus(final Player player, final boolean login) {
        _login = login;
        _charName = player.getName();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xfc);
        writeD(_login ? 1 : 0);
        writeS(_charName);
        writeD(0);
    }
}
