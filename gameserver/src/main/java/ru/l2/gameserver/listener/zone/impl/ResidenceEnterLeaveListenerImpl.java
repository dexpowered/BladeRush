package ru.l2.gameserver.listener.zone.impl;

import ru.l2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Zone;
import ru.l2.gameserver.model.entity.residence.Residence;
import ru.l2.gameserver.stats.Stats;
import ru.l2.gameserver.stats.funcs.FuncMul;

public class ResidenceEnterLeaveListenerImpl implements OnZoneEnterLeaveListener {
    public static final OnZoneEnterLeaveListener STATIC = new ResidenceEnterLeaveListenerImpl();

    @Override
    public void onZoneEnter(final Zone zone, final Creature actor) {
        if (!actor.isPlayer()) {
            return;
        }
        final Player player = (Player) actor;
        final Residence residence = (Residence) zone.getParams().get("residence");
        if (residence.getOwner() == null || residence.getOwner() != player.getClan()) {
            return;
        }
        if (residence.isFunctionActive(3)) {
            final double value = 1.0 + residence.getFunction(3).getLevel() / 100.0;
            player.addStatFunc(new FuncMul(Stats.REGENERATE_HP_RATE, 48, residence, value));
        }
        if (residence.isFunctionActive(4)) {
            final double value = 1.0 + residence.getFunction(4).getLevel() / 100.0;
            player.addStatFunc(new FuncMul(Stats.REGENERATE_MP_RATE, 48, residence, value));
        }
    }

    @Override
    public void onZoneLeave(final Zone zone, final Creature actor) {
        if (!actor.isPlayer()) {
            return;
        }
        final Residence residence = (Residence) zone.getParams().get("residence");
        actor.removeStatsOwner(residence);
    }
}
