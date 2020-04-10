package ai.primeval_isle;

import ru.l2.gameserver.ai.DefaultAI;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.tables.SkillTable;

public class SprigantStun extends DefaultAI {
    private static final int TICK_IN_MILISECONDS = 15000;
    private final Skill SKILL;
    private long _waitTime;

    public SprigantStun(final NpcInstance actor) {
        super(actor);
        SKILL = SkillTable.getInstance().getInfo(5085, 1);
    }

    @Override
    protected boolean thinkActive() {
        if (System.currentTimeMillis() > _waitTime) {
            final NpcInstance actor = getActor();
            actor.doCast(SKILL, actor, false);
            _waitTime = System.currentTimeMillis() + 15000L;
            return true;
        }
        return false;
    }
}
