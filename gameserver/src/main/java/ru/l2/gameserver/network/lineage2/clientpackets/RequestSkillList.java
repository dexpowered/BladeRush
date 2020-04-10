package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.SkillList;

public final class RequestSkillList extends L2GameClientPacket {
    private static final String _C__50_REQUESTSKILLLIST = "[C] 50 RequestSkillList";

    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player cha = getClient().getActiveChar();
        if (cha != null) {
            cha.sendPacket(new SkillList(cha));
        }
    }

    @Override
    public String getType() {
        return "[C] 50 RequestSkillList";
    }
}
