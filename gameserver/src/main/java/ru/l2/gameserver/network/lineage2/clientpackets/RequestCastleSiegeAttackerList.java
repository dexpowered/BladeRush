package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.xml.holder.ResidenceHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.residence.Residence;
import ru.l2.gameserver.network.lineage2.serverpackets.CastleSiegeAttackerList;

public class RequestCastleSiegeAttackerList extends L2GameClientPacket {
    private int _unitId;

    @Override
    protected void readImpl() {
        _unitId = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Residence residence = ResidenceHolder.getInstance().getResidence(_unitId);
        if (residence != null) {
            sendPacket(new CastleSiegeAttackerList(residence));
        }
    }
}
