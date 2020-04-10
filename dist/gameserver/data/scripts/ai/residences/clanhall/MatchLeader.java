package ai.residences.clanhall;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.tables.SkillTable;

public class MatchLeader extends MatchFighter {
    public static final Skill ATTACK_SKILL = SkillTable.getInstance().getInfo(4077, 6);

    public MatchLeader(final NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtAttacked(final Creature attacker, final int dam) {
        super.onEvtAttacked(attacker, dam);
        if (Rnd.chance(10)) {
            addTaskCast(attacker, MatchLeader.ATTACK_SKILL);
        }
    }
}
