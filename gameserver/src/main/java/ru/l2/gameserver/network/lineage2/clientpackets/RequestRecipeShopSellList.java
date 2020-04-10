package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.RecipeShopSellList;

public class RequestRecipeShopSellList extends L2GameClientPacket {
    int _manufacturerId;

    @Override
    protected void readImpl() {
        _manufacturerId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isActionsDisabled()) {
            activeChar.sendActionFailed();
            return;
        }
        final Player manufacturer = (Player) activeChar.getVisibleObject(_manufacturerId);
        if (manufacturer == null || manufacturer.getPrivateStoreType() != 5 || !manufacturer.isInActingRange(activeChar)) {
            activeChar.sendActionFailed();
            return;
        }
        activeChar.sendPacket(new RecipeShopSellList(activeChar, manufacturer));
    }
}
