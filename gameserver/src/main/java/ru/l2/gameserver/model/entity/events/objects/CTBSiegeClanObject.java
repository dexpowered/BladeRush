package ru.l2.gameserver.model.entity.events.objects;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import ru.l2.gameserver.data.dao.SiegePlayerDAO;
import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.events.impl.SiegeEvent;
import ru.l2.gameserver.model.entity.residence.Residence;
import ru.l2.gameserver.model.pledge.Clan;

public class CTBSiegeClanObject extends SiegeClanObject {
    private final TIntSet _players;
    private long _npcId;

    public CTBSiegeClanObject(final String type, final Clan clan, final long param, final long date) {
        super(type, clan, param, date);
        _players = new TIntHashSet();
        _npcId = param;
    }

    public CTBSiegeClanObject(final String type, final Clan clan, final long param) {
        this(type, clan, param, System.currentTimeMillis());
    }

    public void select(final Residence r) {
        _players.addAll(SiegePlayerDAO.getInstance().select(r, getObjectId()));
    }

    public TIntSet getPlayers() {
        return _players;
    }

    @Override
    public void setEvent(final boolean start, final SiegeEvent event) {
        for (final int i : _players.toArray()) {
            final Player player = GameObjectsStorage.getPlayer(i);
            if (player != null) {
                if (start) {
                    player.addEvent(event);
                } else {
                    player.removeEvent(event);
                }
                player.broadcastCharInfo();
            }
        }
    }

    @Override
    public boolean isParticle(final Player player) {
        return _players.contains(player.getObjectId());
    }

    @Override
    public long getParam() {
        return _npcId;
    }

    public void setParam(final int npcId) {
        _npcId = npcId;
    }
}
