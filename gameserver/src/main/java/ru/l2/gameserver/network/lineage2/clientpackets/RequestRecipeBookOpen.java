package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.RecipeBookItemList;

public class RequestRecipeBookOpen extends L2GameClientPacket {
    private boolean isDwarvenCraft;

    @Override
    protected void readImpl() {
        if (_buf.hasRemaining()) {
            isDwarvenCraft = (readD() == 0);
        }
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        sendPacket(new RecipeBookItemList(activeChar, isDwarvenCraft));
    }
}
