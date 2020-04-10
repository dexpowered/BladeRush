package quests;

import ru.l2.gameserver.listener.script.OnInitScriptListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.model.quest.Quest;
import ru.l2.gameserver.model.quest.QuestState;
import ru.l2.gameserver.network.lineage2.serverpackets.MagicSkillUse;
import ru.l2.gameserver.tables.SkillTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Solution
 * 19.08.2018
 * 22:10
 */

public class _778_MobBuff extends Quest implements OnInitScriptListener {

    private static final int mobs = 51001;
    private static final int chans[] = {15,100};
    int[] skill = {4350,1};
    Logger LOGGER = LoggerFactory.getLogger(_778_MobBuff.class);

    public _778_MobBuff() {
        super(false);
        addKillId(mobs);
        LOGGER.info("Хуйня грузитя");
    }

    @Override
    public String onKill(NpcInstance npc, Player killer, QuestState qs) {
        killer.getInventory().addItem(57,10000);
            SkillTable.getInstance().getInfo(skill[0], skill[1]).getEffects(killer, killer, false, false);
            killer.broadcastPacket(new MagicSkillUse(killer, killer, 4350, 1, 3, 0));
            LOGGER.info("ХУйня работает");
        return null;
    }

}