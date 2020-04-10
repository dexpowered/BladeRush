package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.SevenSigns;
import ru.l2.gameserver.network.lineage2.serverpackets.SSQStatus;

public class RequestSSQStatus extends L2GameClientPacket {
    private int _page;

    @Override
    protected void readImpl() {
        _page = readC();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if ((SevenSigns.getInstance().isSealValidationPeriod() || SevenSigns.getInstance().isCompResultsPeriod()) && _page == 4) {
            return;
        }
        activeChar.sendPacket(new SSQStatus(activeChar, _page));
    }
}
