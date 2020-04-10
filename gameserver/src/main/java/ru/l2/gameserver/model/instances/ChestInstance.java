package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class ChestInstance extends MonsterInstance {
    public ChestInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    public void tryOpen(final Player opener, final Skill skill) {
        getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, opener, 100);
    }

    @Override
    public boolean canChampion() {
        return false;
    }
}
