package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.cache.CrestCache;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.pledge.Alliance;

public class RequestSetAllyCrest extends L2GameClientPacket {
    private int _length;
    private byte[] _data;

    @Override
    protected void readImpl() {
        _length = readD();
        if (_length == CrestCache.ALLY_CREST_SIZE && _length == _buf.remaining()) {
            readB(_data = new byte[_length]);
        }
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Alliance ally = activeChar.getAlliance();
        if (ally != null && activeChar.isAllyLeader()) {
            int crestId = 0;
            if (_data != null && CrestCache.isValidCrestData(_data, _length)) {
                crestId = CrestCache.getInstance().saveAllyCrest(ally.getAllyId(), _data);
            } else if (ally.hasAllyCrest()) {
                CrestCache.getInstance().removeAllyCrest(ally.getAllyId());
            } else {
                LOGGER.warn("Character : {} tryed set wrong crest data check him!", activeChar.toString());
            }
            ally.setAllyCrestId(crestId);
            ally.broadcastAllyStatus();
        }
    }
}
