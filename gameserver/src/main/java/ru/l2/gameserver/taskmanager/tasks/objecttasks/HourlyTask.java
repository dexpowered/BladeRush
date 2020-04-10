package ru.l2.gameserver.taskmanager.tasks.objecttasks;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;

/**
 * Created by JunkyFunky
 * on 09.03.2018 11:49
 * group j2dev
 */
public class HourlyTask extends RunnableImpl {
    private final HardReference<Player> _playerRef;

    public HourlyTask(final Player player) {
        _playerRef = player.getRef();
    }

    @Override
    public void runImpl() {
        final Player player = _playerRef.get();
        if (player == null) {
            return;
        }
        final int hoursInGame = player.getHoursInGame();
        player.sendPacket(new SystemMessage(764).addNumber(hoursInGame));
    }
}
