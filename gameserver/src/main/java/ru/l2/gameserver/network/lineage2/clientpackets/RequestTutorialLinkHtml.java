package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;

public class RequestTutorialLinkHtml extends L2GameClientPacket {
    String _bypass;

    @Override
    protected void readImpl() {
        _bypass = readS();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Quest q = QuestManager.getQuest(255);
        final Quest q1 = QuestManager.getQuest(777);
        if (q != null) {
            player.processQuestEvent(q.getName(), _bypass, null);
        }
        if (q1 != null) {
            player.processQuestEvent(q1.getName(), _bypass, null);
        }
    }
}
