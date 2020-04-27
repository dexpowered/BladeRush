package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.handler.items.IRefineryHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;

public class RequestConfirmCancelItem extends L2GameClientPacket {
    int _itemObjId;

    @Override
    protected void readImpl() {
        _itemObjId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final ItemInstance item = activeChar.getInventory().getItemByObjectId(_itemObjId);
        final IRefineryHandler refineryHandler = activeChar.getRefineryHandler();
        if (item == null || refineryHandler == null) {
            activeChar.sendActionFailed();
            activeChar.sendPacket(Msg.THIS_IS_NOT_A_SUITABLE_ITEM);
            return;
        }
        refineryHandler.onPutTargetCancelItem(activeChar, item);
    }
}
