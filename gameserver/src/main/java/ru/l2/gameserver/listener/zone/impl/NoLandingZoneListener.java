package ru.l2.gameserver.listener.zone.impl;

import ru.l2.gameserver.data.xml.holder.ResidenceHolder;
import ru.l2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Zone;
import ru.l2.gameserver.model.entity.residence.Residence;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;

public class NoLandingZoneListener implements OnZoneEnterLeaveListener {
    public static final OnZoneEnterLeaveListener STATIC = new NoLandingZoneListener();

    @Override
    public void onZoneEnter(final Zone zone, final Creature actor) {
        final Player player = actor.getPlayer();
        if (player != null && player.isFlying() && player.getMountNpcId() == 12621) {
            final Residence residence = ResidenceHolder.getInstance().getResidence(zone.getParams().getInteger("residence", 0));
            if (residence == null || player.getClan() == null || residence.getOwner() != player.getClan()) {
                player.stopMove();
                player.sendPacket(SystemMsg.THIS_AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_ATOP_OF_A_WYVERN);
                player.setMount(0, 0, 0);
            }
        }
    }

    @Override
    public void onZoneLeave(final Zone zone, final Creature cha) {
    }
}
