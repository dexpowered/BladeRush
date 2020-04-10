package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.manager.QuestManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;
import ru.l2.gameserver.model.quest.QuestState;
import ru.l2.gameserver.network.lineage2.serverpackets.ExQuestNpcLogList;

public class RequestAddExpandQuestAlarm extends L2GameClientPacket {
    private int _questId;

    @Override
    protected void readImpl() {
        _questId = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Quest quest = QuestManager.getQuest(_questId);
        if (quest == null) {
            return;
        }
        final QuestState state = player.getQuestState(quest.getClass());
        if (state == null) {
            return;
        }
        player.sendPacket(new ExQuestNpcLogList(state));
    }
}
