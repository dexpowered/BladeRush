package npc.model;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public final class PassagewayMobWithHerbInstance extends MonsterInstance {
    public static final int FieryDemonBloodHerb = 9849;

    public PassagewayMobWithHerbInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void calculateRewards(final Creature lastAttacker) {
        if (lastAttacker == null) {
            return;
        }
        super.calculateRewards(lastAttacker);
        if (lastAttacker.isPlayable()) {
            dropItem(lastAttacker.getPlayer(), 9849, 1L);
        }
    }
}
