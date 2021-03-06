package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.CrestCache;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.PledgeCrest;

public class RequestPledgeCrest extends L2GameClientPacket {
    private int _crestId;

    @Override
    protected void readImpl() {
        _crestId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (_crestId == 0) {
            return;
        }
        final byte[] data = CrestCache.getInstance().getPledgeCrest(_crestId);
        if (data != null) {
            final PledgeCrest pc = new PledgeCrest(_crestId, data);
            sendPacket(pc);
        }
    }
}
