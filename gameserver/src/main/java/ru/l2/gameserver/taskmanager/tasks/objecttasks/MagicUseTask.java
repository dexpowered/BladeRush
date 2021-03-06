package ru.l2.gameserver.taskmanager.tasks.objecttasks;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;

/**
 * Created by JunkyFunky
 * on 09.03.2018 11:53
 * group j2dev
 */
public class MagicUseTask extends RunnableImpl {
    public final boolean _forceUse;
    private final HardReference<? extends Creature> _charRef;

    public MagicUseTask(final Creature cha, final boolean forceUse) {
        _charRef = cha.getRef();
        _forceUse = forceUse;
    }

    @Override
    public void runImpl() {
        final Creature character = _charRef.get();
        if (character == null) {
            return;
        }
        final Skill castingSkill = character.getCastingSkill();
        final Creature castingTarget = character.getCastingTarget();
        if (castingSkill == null || castingTarget == null) {
            character.clearCastVars();
            return;
        }
        character.onMagicUseTimer(castingTarget, castingSkill, _forceUse);
    }
}
