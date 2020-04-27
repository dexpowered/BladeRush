package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Player;

public class RequestExCancelEnchantItem extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar != null) {
            activeChar.setEnchantScroll(null);
            activeChar.sendPacket(Msg.INAPPROPRIATE_ENCHANT_CONDITIONS);
        }
    }
}
