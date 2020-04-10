package ru.l2.gameserver.taskmanager.tasks.objecttasks;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.model.Creature;

/**
 * Created by JunkyFunky
 * on 09.03.2018 11:52
 * group j2dev
 */
public class CastEndTimeTask extends RunnableImpl {
    private final HardReference<? extends Creature> _charRef;

    public CastEndTimeTask(final Creature character) {
        _charRef = character.getRef();
    }

    @Override
    public void runImpl() {
        final Creature character = _charRef.get();
        if (character == null) {
            return;
        }
        character.onCastEndTime();
    }
}
