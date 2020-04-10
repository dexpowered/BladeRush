package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Mystic;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.tables.SkillTable;

import java.util.Collections;
import java.util.List;

public class TriolsRevelation extends Mystic {
    private static final int TriolSkillActivationChance = 3;
    private static final int SkillSearchRadius = 500;
    private static final Skill theSkill = SkillTable.getInstance().getInfo(4088, 8);

    public TriolsRevelation(final NpcInstance actor) {
        super(actor);
        actor.startImmobilized();
        AI_TASK_ATTACK_DELAY = 1250L;
    }

    @Override
    protected boolean thinkActive() {
        final NpcInstance actor = getActor();
        if (actor.isDead() || !Rnd.chance(TriolsRevelation.TriolSkillActivationChance)) {
            return false;
        }
        final List<Player> players = World.getAroundPlayers(actor, TriolsRevelation.SkillSearchRadius, 200);
        if (players == null || players.isEmpty()) {
            return false;
        }
        Collections.shuffle(players);
        for (final Player player : players) {
            if (player.getEffectList().getEffectsCountForSkill(TriolsRevelation.theSkill.getId()) < 1) {
                TriolsRevelation.theSkill.getEffects(actor, player, false, false);
                break;
            }
        }
        return true;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
