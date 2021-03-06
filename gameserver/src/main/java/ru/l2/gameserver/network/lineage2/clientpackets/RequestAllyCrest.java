package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.CrestCache;
import ru.l2.gameserver.network.lineage2.serverpackets.AllianceCrest;

public class RequestAllyCrest extends L2GameClientPacket {
    private int _crestId;

    @Override
    protected void readImpl() {
        _crestId = readD();
    }

    @Override
    protected void runImpl() {
        if (_crestId == 0) {
            return;
        }
        final byte[] data = CrestCache.getInstance().getAllyCrest(_crestId);
        if (data != null) {
            final AllianceCrest ac = new AllianceCrest(_crestId, data);
            sendPacket(ac);
        }
    }
}
