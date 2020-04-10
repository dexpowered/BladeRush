package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.CursedWeaponsManager;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.CursedWeapon;
import ru.l2.gameserver.network.lineage2.serverpackets.ExCursedWeaponLocation;
import ru.l2.gameserver.network.lineage2.serverpackets.ExCursedWeaponLocation.CursedWeaponInfo;
import ru.l2.gameserver.utils.Location;

import java.util.ArrayList;
import java.util.List;

public class RequestCursedWeaponLocation extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Creature activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final List<CursedWeaponInfo> list = new ArrayList<>();
        for (final CursedWeapon cw : CursedWeaponsManager.getInstance().getCursedWeapons()) {
            final Location pos = cw.getWorldPosition();
            if (pos != null) {
                list.add(new CursedWeaponInfo(pos, cw.getItemId(), cw.isActivated() ? 1 : 0));
            }
        }
        activeChar.sendPacket(new ExCursedWeaponLocation(list));
    }
}
