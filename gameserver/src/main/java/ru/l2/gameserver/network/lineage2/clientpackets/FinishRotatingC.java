package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.FinishRotating;

public class FinishRotatingC extends L2GameClientPacket {
    private int _degree;
    private int _unknown;

    @Override
    protected void readImpl() {
        _degree = readD();
        _unknown = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        activeChar.broadcastPacket(new FinishRotating(activeChar, _degree, 0));
    }
}
