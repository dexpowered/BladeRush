package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.StopMove;
import ru.l2.gameserver.utils.Location;

public class CannotMoveAnymore extends L2GameClientPacket {
    private final Location _loc;

    public CannotMoveAnymore() {
        _loc = new Location();
    }

    @Override
    protected void readImpl() {
        _loc.x = readD();
        _loc.y = readD();
        _loc.z = readD();
        _loc.h = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isOlyObserver()) {
            activeChar.sendPacket(new StopMove(activeChar.getObjectId(), _loc));
            return;
        }
        if (!activeChar.isOutOfControl()) {
            activeChar.getAI().notifyEvent(CtrlEvent.EVT_ARRIVED_BLOCKED, _loc, null);
            activeChar.stopMove();
        }
    }
}
