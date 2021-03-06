package ru.l2.gameserver.taskmanager.tasks.objecttasks;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;

/**
 * Created by JunkyFunky
 * on 09.03.2018 11:52
 * group j2dev
 */
public class AltMagicUseTask extends RunnableImpl {
    public final Skill _skill;
    private final HardReference<? extends Creature> _charRef;
    private final HardReference<? extends Creature> _targetRef;

    public AltMagicUseTask(final Creature character, final Creature target, final Skill skill) {
        _charRef = character.getRef();
        _targetRef = target.getRef();
        _skill = skill;
    }

    @Override
    public void runImpl() {
        final Creature cha;
        final Creature target;
        if ((cha = _charRef.get()) == null || (target = _targetRef.get()) == null) {
            return;
        }
        cha.altOnMagicUseTimer(target, _skill);
    }
}
