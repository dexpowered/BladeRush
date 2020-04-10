package ru.l2.gameserver.model.entity;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.data.xml.holder.InstantZoneHolder;
import ru.l2.gameserver.manager.DimensionalRiftManager;
import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.InstantZone;
import ru.l2.gameserver.utils.Location;

import java.util.concurrent.Future;

public class DelusionChamber extends DimensionalRift {
    private Future<?> killRiftTask;

    public DelusionChamber(final Party party, final int type, final int room) {
        super(party, type, room);
    }

    @Override
    public synchronized void createNewKillRiftTimer() {
        if (killRiftTask != null) {
            killRiftTask.cancel(false);
            killRiftTask = null;
        }
        killRiftTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl() {
            @Override
            public void runImpl() {
                if (getParty() != null && !getParty().getPartyMembers().isEmpty()) {
                    getParty().getPartyMembers().stream().filter(p -> p.getReflection() == DelusionChamber.this).forEach(p -> {
                        final String var = p.getVar("backCoords");
                        if (var == null) {
                            return;
                        }
                        if ("".equals(var)) {
                            return;
                        }
                        p.teleToLocation(Location.parseLoc(var), ReflectionManager.DEFAULT);
                        p.unsetVar("backCoords");
                    });
                }
                collapse();
            }
        }, 100L);
    }

    @Override
    public void partyMemberExited(final Player player) {
        if (getPlayersInside(false) < 2 || getPlayersInside(true) == 0) {
            createNewKillRiftTimer();
        }
    }

    @Override
    public void manualExitRift(final Player player, final NpcInstance npc) {
        if (!player.isInParty() || player.getParty().getReflection() != this) {
            return;
        }
        if (!player.getParty().isLeader(player)) {
            DimensionalRiftManager.getInstance().showHtmlFile(player, "rift/NotPartyLeader.htm", npc);
            return;
        }
        createNewKillRiftTimer();
    }

    @Override
    public String getName() {
        final InstantZone iz = InstantZoneHolder.getInstance().getInstantZone(_roomType + 120);
        return iz.getName();
    }

    @Override
    protected int getManagerId() {
        return 32664;
    }
}
