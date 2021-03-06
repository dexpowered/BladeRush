package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.PrivateStoreManageListBuy;
import ru.l2.gameserver.utils.TradeHelper;

public class RequestPrivateStoreBuyManageList extends L2GameClientPacket {
    @Override
    protected void readImpl() {
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
        if (activeChar.getSittingTask()) {
            activeChar.sendActionFailed();
            return;
        }
        if (activeChar.isInStoreMode()) {
            activeChar.setPrivateStoreType(Player.STORE_PRIVATE_NONE);
            activeChar.standUp();
            activeChar.broadcastCharInfo();
        } else if (!TradeHelper.checksIfCanOpenStore(activeChar, 3)) {
            activeChar.sendActionFailed();
            return;
        }
        activeChar.sendPacket(new PrivateStoreManageListBuy(activeChar));
    }
}
