package ai.residences.clanhall;

import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.tables.SkillTable;

public class MatchTrief extends MatchFighter {
    public static final Skill HOLD = SkillTable.getInstance().getInfo(4047, 6);

    public MatchTrief(final NpcInstance actor) {
        super(actor);
    }

    public void hold() {
        final NpcInstance actor = getActor();
        addTaskCast(actor, MatchTrief.HOLD);
        doTask();
    }
}
