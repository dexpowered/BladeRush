package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.pledge.Clan;

public class PledgeInfo extends L2GameServerPacket {
    private final int clan_id;
    private final String clan_name;
    private final String ally_name;

    public PledgeInfo(final Clan clan) {
        clan_id = clan.getClanId();
        clan_name = clan.getName();
        ally_name = ((clan.getAlliance() == null) ? "" : clan.getAlliance().getAllyName());
    }

    @Override
    protected final void writeImpl() {
        writeC(0x83);
        writeD(clan_id);
        writeS(clan_name);
        writeS(ally_name);
    }
}
