package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.network.lineage2.serverpackets.ExCursedWeaponList;

public class RequestCursedWeaponList extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Creature activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        activeChar.sendPacket(new ExCursedWeaponList());
    }
}
