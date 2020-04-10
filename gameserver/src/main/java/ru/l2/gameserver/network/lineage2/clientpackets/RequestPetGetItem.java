package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Summon;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.utils.ItemFunctions;

public class RequestPetGetItem extends L2GameClientPacket {
    private int _objectId;

    @Override
    protected void readImpl() {
        _objectId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isOutOfControl()) {
            activeChar.sendActionFailed();
            return;
        }
        final Summon summon = activeChar.getPet();
        if (summon == null || !summon.isPet() || summon.isDead() || summon.isActionsDisabled()) {
            activeChar.sendActionFailed();
            return;
        }
        final ItemInstance item = (ItemInstance) activeChar.getVisibleObject(_objectId);
        if (item == null) {
            activeChar.sendActionFailed();
            return;
        }
        if (!ItemFunctions.checkIfCanPickup(summon, item)) {
            SystemMessage sm;
            if (item.getItemId() == 57) {
                sm = new SystemMessage(55);
                sm.addNumber(item.getCount());
            } else {
                sm = new SystemMessage(56);
                sm.addItemName(item.getItemId());
            }
            sendPacket(sm);
            activeChar.sendActionFailed();
            return;
        }
        summon.getAI().setIntention(CtrlIntention.AI_INTENTION_PICK_UP, item, null);
    }
}
