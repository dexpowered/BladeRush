package npc.model;

import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public final class SpecialMinionInstance extends MonsterInstance {
    public SpecialMinionInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public boolean isFearImmune() {
        return true;
    }

    @Override
    public boolean isParalyzeImmune() {
        return true;
    }

    @Override
    public boolean isLethalImmune() {
        return true;
    }

    @Override
    public boolean canChampion() {
        return false;
    }

    @Override
    public void onRandomAnimation() {
    }
}
