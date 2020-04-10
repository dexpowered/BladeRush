package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public final class ArtefactInstance extends NpcInstance {
    public ArtefactInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
        setHasChatWindow(false);
    }

    @Override
    public boolean isArtefact() {
        return true;
    }

    @Override
    public boolean isAutoAttackable(final Creature attacker) {
        return false;
    }

    @Override
    public boolean isAttackable(final Creature attacker) {
        return false;
    }

    @Override
    public boolean isInvul() {
        return true;
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
}
