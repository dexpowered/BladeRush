package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;
import ru.l2.gameserver.model.quest.QuestState;

public class RequestDestroyQuest extends L2GameClientPacket {
    private int _questID;

    @Override
    protected void readImpl() {
        _questID = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        final Quest quest = QuestManager.getQuest(_questID);
        if (activeChar == null || quest == null) {
            return;
        }
        if (!quest.canAbortByPacket()) {
            return;
        }
        final QuestState qs = activeChar.getQuestState(quest.getClass());
        if (qs != null && !qs.isCompleted()) {
            qs.abortQuest();
        }
    }
}
