package ru.l2.gameserver.taskmanager.tasks.objecttasks;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.model.Player;

/**
 * Created by JunkyFunky
 * on 09.03.2018 11:50
 * group j2dev
 */
public class KickTask extends RunnableImpl {
    private final HardReference<Player> _playerRef;

    public KickTask(final Player player) {
        _playerRef = player.getRef();
    }

    @Override
    public void runImpl() {
        final Player player = _playerRef.get();
        if (player == null) {
            return;
        }
        player.setOfflineMode(false);
        player.kick();
    }
}
