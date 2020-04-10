package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;

public class RequestTutorialQuestionMarkPressed extends L2GameClientPacket {
    int _number;

    public RequestTutorialQuestionMarkPressed() {
        _number = 0;
    }

    @Override
    protected void readImpl() {
        _number = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Quest q = QuestManager.getQuest(255);
        if (q != null) {
            player.processQuestEvent(q.getName(), "QM" + _number, null);
        }
    }
}
