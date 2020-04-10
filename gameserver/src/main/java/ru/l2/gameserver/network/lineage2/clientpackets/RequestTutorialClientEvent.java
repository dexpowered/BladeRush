package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;

public class RequestTutorialClientEvent extends L2GameClientPacket {
    int event;

    public RequestTutorialClientEvent() {
        event = 0;
    }

    @Override
    protected void readImpl() {
        event = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Quest tutorial = QuestManager.getQuest(255);
        if (tutorial != null) {
            player.processQuestEvent(tutorial.getName(), "CE" + event, null);
        }
    }
}
