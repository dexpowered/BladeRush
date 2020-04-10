package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.pledge.RankPrivs;
import ru.l2.gameserver.model.pledge.UnitMember;

public class PledgeReceivePowerInfo extends L2GameServerPacket {
    private final int PowerGrade;
    private final int privs;
    private final String member_name;

    public PledgeReceivePowerInfo(final UnitMember member) {
        PowerGrade = member.getPowerGrade();
        member_name = member.getName();
        if (member.isClanLeader()) {
            privs = 8388606;
        } else {
            final RankPrivs temp = member.getClan().getRankPrivs(member.getPowerGrade());
            if (temp != null) {
                privs = temp.getPrivs();
            } else {
                privs = 0;
            }
        }
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x3c);
        writeD(PowerGrade);
        writeS(member_name);
        writeD(privs);
    }
}
