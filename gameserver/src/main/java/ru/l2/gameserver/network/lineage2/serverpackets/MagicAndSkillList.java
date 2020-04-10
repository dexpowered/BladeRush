package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Creature;

public class MagicAndSkillList extends L2GameServerPacket {
    private final int _chaId;
    private final int _unk1;
    private final int _unk2;

    public MagicAndSkillList(final Creature cha, final int unk1, final int unk2) {
        _chaId = cha.getObjectId();
        _unk1 = unk1;
        _unk2 = unk2;
    }

    @Override
    protected final void writeImpl() {
        writeC(0x40);
        writeD(_chaId);
        writeD(_unk1);
        writeD(_unk2);
    }
}
