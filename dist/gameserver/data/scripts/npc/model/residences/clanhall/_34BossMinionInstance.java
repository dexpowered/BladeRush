package npc.model.residences.clanhall;

import npc.model.residences.SiegeGuardInstance;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.data.scripts.Functions;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public abstract class _34BossMinionInstance extends SiegeGuardInstance implements _34SiegeGuard {
    public _34BossMinionInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onDeath(final Creature killer) {
        setCurrentHp(1.0, true);
    }

    @Override
    public void onSpawn() {
        super.onSpawn();
        Functions.npcShout(this, spawnChatSay());
    }

    public abstract String spawnChatSay();

    @Override
    public abstract String teleChatSay();
}
