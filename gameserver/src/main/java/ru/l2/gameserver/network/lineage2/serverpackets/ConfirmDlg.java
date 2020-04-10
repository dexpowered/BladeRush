package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.network.lineage2.components.SystemMsg;

public class ConfirmDlg extends SysMsgContainer<ConfirmDlg> {
    private final int _time;
    private int _requestId;

    public ConfirmDlg(final SystemMsg msg, final int time) {
        super(msg);
        _time = time;
    }

    @Override
    protected final void writeImpl() {
        writeC(0xed);
        writeElements();
        writeD(_time);
        writeD(_requestId);
    }

    public void setRequestId(final int requestId) {
        _requestId = requestId;
    }
}
