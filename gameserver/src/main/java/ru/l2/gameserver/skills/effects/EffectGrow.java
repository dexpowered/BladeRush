package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.stats.Env;

public final class EffectGrow extends Effect {
    public EffectGrow(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (_effected.isNpc()) {
            final NpcInstance npc = (NpcInstance) _effected;
            npc.setCollisionHeight(npc.getCollisionHeight() * 1.24);
            npc.setCollisionRadius(npc.getCollisionRadius() * 1.19);
        }
    }

    @Override
    public void onExit() {
        super.onExit();
        if (_effected.isNpc()) {
            final NpcInstance npc = (NpcInstance) _effected;
            npc.setCollisionHeight(npc.getTemplate().getCollisionHeight());
            npc.setCollisionRadius(npc.getTemplate().getCollisionRadius());
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
