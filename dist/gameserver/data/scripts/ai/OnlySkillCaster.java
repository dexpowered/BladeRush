package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Mystic;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.tables.SkillTable;

public class OnlySkillCaster extends Mystic {

    private static final Skill skillToCast = SkillTable.getInstance().getInfo(4100, 1);
    private static final double chanceToCast = 99.9;

    public OnlySkillCaster(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean createNewTask() {
        final NpcInstance actor = getActor();
        if (actor == null) {
            return false;
        }

        clearTasks();

        final Creature target;
        if ((target = prepareTarget()) == null) {
            return false;
        }

        if (Rnd.chance(chanceToCast) && !actor.isCastingNow() && actor.isInRange(target, skillToCast.getCastRange())) {
            addTaskCast(target, skillToCast);
        }


        return true;
    }

    @Override
    protected boolean randomWalk() {
        return true;
    }

    @Override
    protected boolean randomAnimation() {
        return true;
    }

    @Override
    public boolean canSeeInSilentMove(final Playable target) {
        return true;
    }

    @Override
    public boolean canSeeInHide(final Playable target) {
        return true;
    }
}
