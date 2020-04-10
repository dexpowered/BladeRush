package ai.custom;

import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.entity.Reflection;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.model.instances.ReflectionBossInstance;
import ru.l2.gameserver.stats.Stats;
import ru.l2.gameserver.stats.funcs.FuncSet;

public class LabyrinthLostWarden extends Fighter {
    public LabyrinthLostWarden(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        final NpcInstance actor = getActor();
        final Reflection r = actor.getReflection();
        if (!r.isDefault() && checkMates(actor.getNpcId()) && findLostCaptain() != null) {
            findLostCaptain().addStatFunc(new FuncSet(Stats.POWER_ATTACK, 48, this, findLostCaptain().getTemplate().getBasePAtk() * 0.66));
        }
        super.onEvtDead(killer);
    }

    private boolean checkMates(final int id) {
        for (final NpcInstance n : getActor().getReflection().getNpcs()) {
            if (n.getNpcId() == id && !n.isDead()) {
                return false;
            }
        }
        return true;
    }

    private NpcInstance findLostCaptain() {
        for (final NpcInstance n : getActor().getReflection().getNpcs()) {
            if (n instanceof ReflectionBossInstance) {
                return n;
            }
        }
        return null;
    }
}
