package ru.l2.gameserver.taskmanager;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.threading.SteppingRunnableQueueManager;
import ru.l2.gameserver.ThreadPoolManager;

public class RegenTaskManager extends SteppingRunnableQueueManager {
    private static final RegenTaskManager _instance = new RegenTaskManager();

    private RegenTaskManager() {
        super(1000L);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(this, 1000L, 1000L);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                purge();
            }
        }, 10000L, 10000L);
    }

    public static RegenTaskManager getInstance() {
        return _instance;
    }
}
