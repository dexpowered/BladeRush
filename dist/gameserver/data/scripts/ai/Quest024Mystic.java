package ai;

import quests._024_InhabitantsOfTheForestOfTheDead;
import ru.l2.gameserver.ai.Mystic;
import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.model.quest.Quest;
import ru.l2.gameserver.model.quest.QuestState;

public class Quest024Mystic extends Mystic {
    public Quest024Mystic(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        final Quest q = QuestManager.getQuest(_024_InhabitantsOfTheForestOfTheDead.class);
        if (q != null) {
            for (final Player player : World.getAroundPlayers(getActor(), 300, 200)) {
                final QuestState questState = player.getQuestState(_024_InhabitantsOfTheForestOfTheDead.class);
                if (questState != null && questState.getCond() == 3) {
                    q.notifyEvent("see_creature", questState, getActor());
                }
            }
        }
        return super.thinkActive();
    }
}
