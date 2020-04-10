package ru.l2.gameserver.taskmanager.actionrunner.tasks;

import ru.l2.gameserver.taskmanager.actionrunner.ActionRunner;
import ru.l2.gameserver.taskmanager.actionrunner.ActionWrapper;

public abstract class AutomaticTask extends ActionWrapper {
    public static final String TASKS = "automatic_tasks";

    public AutomaticTask() {
        super(TASKS);
    }

    public abstract void doTask();

    public abstract long reCalcTime(final boolean p0);

    @Override
    public void runImpl0() {
        try {
            doTask();
        } finally {
            ActionRunner.getInstance().register(reCalcTime(false), this);
        }
    }
}
