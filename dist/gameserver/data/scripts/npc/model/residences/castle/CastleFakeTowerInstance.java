package npc.model.residences.castle;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class CastleFakeTowerInstance extends NpcInstance {
    public CastleFakeTowerInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public boolean isAutoAttackable(final Creature player) {
        return false;
    }

    @Override
    public void showChatWindow(final Player player, final int val, final Object... arg) {
    }

    @Override
    public void showChatWindow(final Player player, final String filename, final Object... replace) {
    }

    @Override
    public boolean canInteractWithKarmaPlayer() {
        return true;
    }

    @Override
    public boolean hasRandomAnimation() {
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
