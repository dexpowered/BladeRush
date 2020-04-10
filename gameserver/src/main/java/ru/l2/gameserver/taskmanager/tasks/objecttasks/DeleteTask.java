package ru.l2.gameserver.taskmanager.tasks.objecttasks;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.model.Creature;

/**
 * Created by JunkyFunky
 * on 09.03.2018 11:48
 * group j2dev
 */
public class DeleteTask extends RunnableImpl {

    private final HardReference<? extends Creature> _ref;

    public DeleteTask(final Creature c) {
        _ref = c.getRef();
    }

    @Override
    public void runImpl() {
        final Creature c = _ref.get();
        if (c != null) {
            c.deleteMe();
        }
    }
}
