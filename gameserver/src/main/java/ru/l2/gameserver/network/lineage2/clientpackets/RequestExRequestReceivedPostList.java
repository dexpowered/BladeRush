package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.ExShowReceivedPostList;

public class RequestExRequestReceivedPostList extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player cha = getClient().getActiveChar();
        if (cha != null) {
            cha.sendPacket(new ExShowReceivedPostList(cha));
        }
    }
}
