package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class ReflectionBossInstance extends RaidBossInstance {
    private static final int COLLAPSE_AFTER_DEATH_TIME = 5;

    public ReflectionBossInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    protected void onDeath(final Creature killer) {
        getMinionList().unspawnMinions();
        super.onDeath(killer);
        clearReflection();
    }

    protected void clearReflection() {
        getReflection().clearReflection(5, true);
    }
}
