package ru.l2.gameserver.model.instances;

import ru.l2.gameserver.templates.npc.NpcTemplate;

public class BossInstance extends RaidBossInstance {
    private boolean _teleportedToNest;

    public BossInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public boolean isBoss() {
        return true;
    }

    @Override
    public final boolean isMovementDisabled() {
        return getNpcId() == 29006 || super.isMovementDisabled();
    }

    public boolean isTeleported() {
        return _teleportedToNest;
    }

    public void setTeleported(final boolean flag) {
        _teleportedToNest = flag;
    }

    @Override
    public boolean hasRandomAnimation() {
        return false;
    }
}
