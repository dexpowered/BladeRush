package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;

public class RequestShortCutDel extends L2GameClientPacket {
    private int _slot;
    private int _page;

    @Override
    protected void readImpl() {
        final int id = readD();
        _slot = id % 12;
        _page = id / 12;
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        activeChar.deleteShortCut(_slot, _page);
    }
}
