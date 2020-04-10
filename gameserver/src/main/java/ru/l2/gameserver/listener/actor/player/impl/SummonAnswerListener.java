package ru.l2.gameserver.listener.actor.player.impl;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.gameserver.listener.actor.player.OnAnswerListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage2;
import ru.l2.gameserver.utils.Location;

public class SummonAnswerListener implements OnAnswerListener {
    private final HardReference<Player> _playerRef;
    private final Location _location;
    private final long _count;
    private final long _timeStamp;

    public SummonAnswerListener(final Player player, final Location loc, final long count, final long expiration) {
        _playerRef = player.getRef();
        _location = loc;
        _count = count;
        _timeStamp = expiration > 0 ? System.currentTimeMillis() + expiration : Long.MAX_VALUE;
    }

    @Override
    public void sayYes() {
        if (System.currentTimeMillis() > _timeStamp) {
            return;
        }

        final Player player = _playerRef.get();
        if (player == null) {
            return;
        }
        player.abortAttack(true, true);
        player.abortCast(true, true);
        player.stopMove();
        if (_count > 0L) {
            if (player.getInventory().destroyItemByItemId(8615, _count)) {
                player.sendPacket(SystemMessage2.removeItems(8615, _count));
                player.teleToLocation(_location);
            } else {
                player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            }
        } else {
            player.teleToLocation(_location);
        }
    }

    @Override
    public void sayNo() {
    }
}
