package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.xml.holder.ResidenceHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.residence.Castle;
import ru.l2.gameserver.network.lineage2.serverpackets.CastleSiegeDefenderList;

public class RequestCastleSiegeDefenderList extends L2GameClientPacket {
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
        final Castle castle = ResidenceHolder.getInstance().getResidence(Castle.class, _unitId);
        if (castle == null || castle.getOwner() == null) {
            return;
        }
        player.sendPacket(new CastleSiegeDefenderList(castle));
    }
}
