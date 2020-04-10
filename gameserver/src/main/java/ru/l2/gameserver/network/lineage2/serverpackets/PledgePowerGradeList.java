package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.pledge.RankPrivs;

import java.util.Collection;

public class PledgePowerGradeList extends L2GameServerPacket {
    private final Collection<RankPrivs> _privs;

    public PledgePowerGradeList(final Collection<RankPrivs> privs) {
        _privs = privs;
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x3b);
        writeD(_privs.size());
        _privs.forEach(element -> {
            writeD(element.getRank());
            writeD(element.getParty());
        });
    }
}
