package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.handler.items.IRefineryHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.ExVariationCancelResult;

public final class RequestRefineCancel extends L2GameClientPacket {
    private int _targetItemObjId;

    @Override
    protected void readImpl() {
        _targetItemObjId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final ItemInstance item = activeChar.getInventory().getItemByObjectId(_targetItemObjId);
        final IRefineryHandler refineryHandler = activeChar.getRefineryHandler();
        if (item == null || refineryHandler == null) {
            activeChar.sendPacket(Msg.THIS_IS_NOT_A_SUITABLE_ITEM, ExVariationCancelResult.FAIL_PACKET);
            return;
        }
        refineryHandler.onRequestCancelRefine(activeChar, item);
    }
}
