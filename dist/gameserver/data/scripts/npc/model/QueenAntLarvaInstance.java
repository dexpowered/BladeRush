package npc.model;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class QueenAntLarvaInstance extends MonsterInstance {
    public QueenAntLarvaInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void reduceCurrentHp(double damage, final Creature attacker, final Skill skill, final boolean awake, final boolean standUp, final boolean directHp, final boolean canReflect, final boolean transferDamage, final boolean isDot, final boolean sendMessage) {
        damage = ((getCurrentHp() - damage > 1.0) ? damage : (getCurrentHp() - 1.0));
        super.reduceCurrentHp(damage, attacker, skill, awake, standUp, directHp, canReflect, transferDamage, isDot, sendMessage);
    }

    @Override
    public boolean canChampion() {
        return false;
    }

    @Override
    public boolean isImmobilized() {
        return true;
    }
}
