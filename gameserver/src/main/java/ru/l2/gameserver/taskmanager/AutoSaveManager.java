package ru.l2.gameserver.taskmanager;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.threading.SteppingRunnableQueueManager;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.model.Player;

import java.util.concurrent.Future;

public class AutoSaveManager extends SteppingRunnableQueueManager {
    private static final AutoSaveManager _instance = new AutoSaveManager();

    private AutoSaveManager() {
        super(10000L);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(this, 10000L, 10000L);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                purge();
            }
        }, 60000L, 60000L);
    }

    public static AutoSaveManager getInstance() {
        return _instance;
    }

    public Future<?> addAutoSaveTask(final Player player) {
        final long delay = Rnd.get(180, 360) * 1000L;
        return scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                if (!player.isOnline()) {
                    return;
                }
                player.store(true);
                if (Config.AUTOSAVE_ITEMS) {
                    player.getInventory().store();
                }
            }
        }, delay, delay);
    }
}
