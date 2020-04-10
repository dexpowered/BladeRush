package zones;


import ru.l2.gameserver.listener.script.OnInitScriptListener;
import ru.l2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Zone;
import ru.l2.gameserver.model.Zone.ZoneType;
import ru.l2.gameserver.model.entity.events.impl.DuelEvent;
import ru.l2.gameserver.model.entity.events.impl.PlayerVsPlayerDuelEvent;
import ru.l2.gameserver.utils.ReflectionUtils;

import java.util.List;

/**
 * @author JunkyFunky
 * - При входе в мирную зону, дуэль заканчивается
 */
public class DuelZone implements OnInitScriptListener {

    @Override
    public void onInit() {
        final ZoneListener _zoneListener = new ZoneListener();
        final List<Zone> zones = ReflectionUtils.getZonesByType(ZoneType.peace_zone);
        zones.forEach(zone -> zone.addListener(_zoneListener));
    }

    public static class ZoneListener implements OnZoneEnterLeaveListener {
        @Override
        public void onZoneEnter(final Zone zone, final Creature cha) {
            if (!cha.isPlayer()) {
                return;
            }

            final Player player = (Player) cha;
            if (!player.isInDuel()) {
                return;
            }

            final DuelEvent duelEvent = player.getEvent(DuelEvent.class);
            if (duelEvent != null && duelEvent instanceof PlayerVsPlayerDuelEvent) {
                duelEvent.stopEvent();
            }
        }

        @Override
        public void onZoneLeave(final Zone zone, final Creature cha) {
        }
    }
}
