package ru.l2.gameserver.taskmanager;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.threading.SteppingRunnableQueueManager;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;

public class EffectTaskManager extends SteppingRunnableQueueManager {
    private static final long TICK = 250L;
    private static final EffectTaskManager[] _instances = new EffectTaskManager[Config.EFFECT_TASK_MANAGER_COUNT];
    private static int randomizer;

    static {
        for (int i = 0; i < _instances.length; ++i) {
            _instances[i] = new EffectTaskManager();
        }
    }

    private EffectTaskManager() {
        super(TICK);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(this, Rnd.get(TICK), TICK);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                purge();
            }
        }, 30000L, 30000L);
    }

    public static EffectTaskManager getInstance() {
        return _instances[randomizer++ & _instances.length - 1];
    }

    public CharSequence getStats(final int num) {
        return _instances[num].getStats();
    }
}
