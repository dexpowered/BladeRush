package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.xml.holder.HennaHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.HennaItemInfo;
import ru.l2.gameserver.templates.Henna;

public class RequestHennaItemInfo extends L2GameClientPacket {
    private int _symbolId;

    @Override
    protected void readImpl() {
        _symbolId = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Henna henna = HennaHolder.getInstance().getHenna(_symbolId);
        if (henna != null) {
            player.sendPacket(new HennaItemInfo(henna, player));
        }
    }
}
