package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.GameTimeController;

public class ClientSetTime extends L2GameServerPacket {
    public static final L2GameServerPacket STATIC = new ClientSetTime();

    @Override
    protected final void writeImpl() {
        writeC(0xec);
        writeD(GameTimeController.getInstance().getGameTime());
        writeD(6);
    }
}
