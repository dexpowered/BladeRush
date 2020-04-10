package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Zone;
import ru.l2.gameserver.model.Zone.ZoneType;
import ru.l2.gameserver.model.entity.events.impl.CastleSiegeEvent;
import ru.l2.gameserver.model.entity.events.impl.SiegeEvent;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerSummonSiegeGolem extends Condition {
    @Override
    protected boolean testImpl(final Env env) {
        final Player player = env.character.getPlayer();
        if (player == null) {
            return false;
        }
        Zone zone = player.getZone(ZoneType.RESIDENCE);
        if (zone != null) {
            return false;
        }
        zone = player.getZone(ZoneType.SIEGE);
        if (zone == null) {
            return false;
        }
        final SiegeEvent event = player.getEvent(SiegeEvent.class);
        if (event == null) {
            return false;
        }
        if (event instanceof CastleSiegeEvent) {
            if (zone.getParams().getInteger("residence") != event.getId()) {
                return false;
            }
            return event.getSiegeClan("attackers", player.getClan()) != null;
        } else return event.getSiegeClan("defenders", player.getClan()) != null;
    }
}
