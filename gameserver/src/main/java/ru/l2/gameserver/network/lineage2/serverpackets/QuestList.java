package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.QuestState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestList extends L2GameServerPacket {
    private final List<int[]> questlist;

    public QuestList(final Player player) {
        final QuestState[] allQuestStates = player.getAllQuestsStates();
        questlist = new ArrayList<>(allQuestStates.length);
        Arrays.stream(allQuestStates).filter(quest -> quest.getQuest().isVisible() && quest.isStarted()).map(quest -> new int[]{quest.getQuest().getQuestIntId(), quest.getInt("cond")}).forEach(questlist::add);
    }

    @Override
    protected final void writeImpl() {
        writeC(0x80);
        writeH(questlist.size());
        questlist.forEach(q -> {
            writeD(q[0]);
            writeD(q[1]);
        });
    }
}
