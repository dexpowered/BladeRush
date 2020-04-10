package ai.tower_of_insolence;


import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.utils.NpcUtils;

/**
 * @author Mangol
 */
public class golkonda_longhorn extends Fighter {
    private final int chest_of_golkonda = 31029;

    public golkonda_longhorn(final NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        NpcUtils.spawnSingle(chest_of_golkonda, getActor().getX(), getActor().getY(), getActor().getZ());
        super.onEvtDead(killer);
    }
}
