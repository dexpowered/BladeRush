package ai;

import ru.l2.gameserver.ai.Guard;
import ru.l2.gameserver.model.instances.NpcInstance;

public class GuardRndWalkAndAnim extends Guard {
    public GuardRndWalkAndAnim(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        return super.thinkActive() || randomAnimation() || randomWalk();
    }
}
