package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;

public class RequestTutorialPassCmdToServer extends L2GameClientPacket {
    String _bypass;

    public RequestTutorialPassCmdToServer() {
        _bypass = null;
    }

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
        final Quest tutorial = QuestManager.getQuest(255);
        if (tutorial != null) {
            player.processQuestEvent(tutorial.getName(), _bypass, null);
        }
    }
}
