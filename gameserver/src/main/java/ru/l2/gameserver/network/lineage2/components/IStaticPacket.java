package ru.l2.gameserver.network.lineage2.components;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.L2GameServerPacket;

public interface IStaticPacket {
    L2GameServerPacket packet(final Player p0);
}
