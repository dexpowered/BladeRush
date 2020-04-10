package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.model.quest.QuestEventType;
import ru.l2.gameserver.model.quest.QuestState;
import ru.l2.gameserver.tables.SkillTable;

import java.util.List;

public class Quest421FairyTree extends Fighter {
    private static final Skill s_quest_vicious_poison = SkillTable.getInstance().getInfo(4243, 1);

    public Quest421FairyTree(final NpcInstance actor) {
        super(actor);
        actor.startImmobilized();
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (attacker != null) {
            if (attacker.isPlayer() && Rnd.chance(29)) {
                s_quest_vicious_poison.getEffects(actor, attacker, false, false);
            } else if (attacker.isPet()) {
                final Player player = attacker.getPlayer();
                if (player != null) {
                    final List<QuestState> quests = player.getQuestsForEvent(actor, QuestEventType.ATTACKED_WITH_QUEST);
                    if (quests != null) {
                        quests.forEach(qs -> qs.getQuest().notifyAttack(actor, qs));
                    }
                }
            }
        }
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }

}
