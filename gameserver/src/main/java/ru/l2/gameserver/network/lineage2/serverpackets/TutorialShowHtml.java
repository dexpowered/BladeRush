package ru.l2.gameserver.network.lineage2.serverpackets;

public class TutorialShowHtml extends L2GameServerPacket {
    private final String _html;

    public TutorialShowHtml(final String html) {
        _html = html;
    }

    @Override
    protected final void writeImpl() {
        writeC(0xa0);
        writeS(_html);
    }
}
