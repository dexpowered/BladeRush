package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.network.lineage2.serverpackets.QuestList;

public class RequestQuestList extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        sendPacket(new QuestList(getClient().getActiveChar()));
    }
}
