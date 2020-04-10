package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Creature;

public class NickNameChanged extends L2GameServerPacket {
    private final int objectId;
    private final String title;

    public NickNameChanged(final Creature cha) {
        objectId = cha.getObjectId();
        title = cha.getTitle();
    }

    @Override
    protected void writeImpl() {
        writeC(0xcc);
        writeD(objectId);
        writeS(title);
    }
}
