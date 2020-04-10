package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.HennaUnequipList;

public class RequestHennaUnequipList extends L2GameClientPacket {
    private int _symbolId;

    @Override
    protected void readImpl() {
        _symbolId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final HennaUnequipList he = new HennaUnequipList(activeChar);
        activeChar.sendPacket(he);
    }
}
