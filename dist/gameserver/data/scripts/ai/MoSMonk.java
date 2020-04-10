package ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;

public class MoSMonk extends Fighter {
    public MoSMonk(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onIntentionAttack(final Creature target) {
        final NpcInstance actor = getActor();
        if (getIntention() == CtrlIntention.AI_INTENTION_ACTIVE && Rnd.chance(20)) {
            actor.MakeFString(1121006); //offlike pts fstring
        }
        super.onIntentionAttack(target);
    }

    @Override
    public boolean checkAggression(final Creature target) {
        return target.getActiveWeaponInstance() != null && super.checkAggression(target);
    }
}
