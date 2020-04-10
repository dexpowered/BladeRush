package npc.model.residences.clanhall;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.entity.events.impl.ClanHallTeamBattleEvent;
import ru.l2.gameserver.model.entity.events.objects.CTBTeamObject;
import ru.l2.gameserver.model.entity.residence.ClanHall;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.Location;

import java.util.List;

public class MatchMassTeleporterInstance extends NpcInstance {
    private final int _flagId;
    private long _timeout;

    public MatchMassTeleporterInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
        _flagId = template.getAIParams().getInteger("flag");
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... arg) {
        final ClanHall clanHall = getClanHall();
        final ClanHallTeamBattleEvent siegeEvent = clanHall.getSiegeEvent();
        if (_timeout > System.currentTimeMillis()) {
            showChatWindow(player, "residence2/clanhall/agit_mass_teleporter001.htm");
            return;
        }
        if (isInActingRange(player)) {
            _timeout = System.currentTimeMillis() + 60000L;
            final List<CTBTeamObject> locs = siegeEvent.getObjects("tryout_part");
            final CTBTeamObject object = locs.get(_flagId);
            if (object.getFlag() != null) {
                for (final Player $player : World.getAroundPlayers(this, 400, 100)) {
                    $player.teleToLocation(Location.findPointToStay(object.getFlag(), 100, 125));
                }
            }
        }
    }
}
