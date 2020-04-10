package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public final class TrainerInstance extends NpcInstance {
    public TrainerInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public String getHtmlPath(final int npcId, final int val, final Player player) {
        String pom;
        if (val == 0) {
            pom = "" + npcId;
        } else {
            pom = npcId + "-" + val;
        }
        if(getTemplate().getHtmRoot() != null) {
            return getTemplate().getHtmRoot()+ pom +".htm";
        }
        return "trainer/" + pom + ".htm";
    }
}
