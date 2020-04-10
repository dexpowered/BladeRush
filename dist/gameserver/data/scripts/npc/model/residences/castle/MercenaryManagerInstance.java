package npc.model.residences.castle;

import ru.l2.gameserver.model.instances.MerchantInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class MercenaryManagerInstance extends MerchantInstance {
    public MercenaryManagerInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public boolean canInteractWithKarmaPlayer() {
        return true;
    }
}
