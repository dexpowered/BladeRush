package handler.items;

import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.tables.PetDataTable;
import ru.l2.gameserver.tables.SkillTable;

public class PetSummon extends ScriptItemHandler {
    private static final int[] _itemIds = PetDataTable.getPetControlItems();
    private static final int _skillId = 2046;

    @Override
    public boolean useItem(final Playable playable, final ItemInstance item, final boolean ctrl) {
        if (playable == null || !playable.isPlayer()) {
            return false;
        }
        final Player player = (Player) playable;
        player.setPetControlItem(item);
        player.getAI().Cast(SkillTable.getInstance().getInfo(_skillId, 1), player, false, true);
        return true;
    }

    @Override
    public final int[] getItemIds() {
        return _itemIds;
    }
}
