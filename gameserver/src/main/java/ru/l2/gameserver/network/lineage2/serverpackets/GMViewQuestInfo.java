package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;
import ru.l2.gameserver.model.quest.QuestState;

import java.util.Arrays;

public class GMViewQuestInfo extends L2GameServerPacket {
    private final Player _cha;

    public GMViewQuestInfo(final Player cha) {
        _cha = cha;
    }

    @Override
    protected final void writeImpl() {
        writeC(0x93);
        writeS(_cha.getName());
        final Quest[] quests = _cha.getAllActiveQuests();
        if (quests.length == 0) {
            writeC(0);
            writeH(0);
            writeH(0);
            return;
        }
        writeH(quests.length);
        Arrays.stream(quests).forEach(q -> {
            writeD(q.getQuestIntId());
            final QuestState qs = _cha.getQuestState(q.getName());
            writeD((qs == null) ? 0 : qs.getInt("cond"));
        });
    }
}
