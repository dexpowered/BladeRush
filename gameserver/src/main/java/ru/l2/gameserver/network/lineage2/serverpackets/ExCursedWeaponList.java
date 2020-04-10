package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.manager.CursedWeaponsManager;

public class ExCursedWeaponList extends L2GameServerPacket {
    private final int[] cursedWeapon_ids;

    public ExCursedWeaponList() {
        cursedWeapon_ids = CursedWeaponsManager.getInstance().getCursedWeaponsIds();
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x45);
        writeDD(cursedWeapon_ids, true);
    }
}
