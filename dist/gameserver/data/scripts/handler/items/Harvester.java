package handler.items;

import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.tables.SkillTable;

public class Harvester extends SimpleItemHandler {
    private static final int[] ITEM_IDS = {5125};

    @Override
    public int[] getItemIds() {
        return ITEM_IDS;
    }

    @Override
    protected boolean useItemImpl(final Player player, final ItemInstance item, final boolean ctrl) {
        final GameObject target = player.getTarget();
        if (target == null || !target.isMonster()) {
            player.sendPacket(SystemMsg.THAT_IS_AN_INCORRECT_TARGET);
            return false;
        }
        final MonsterInstance monster = (MonsterInstance) player.getTarget();
        if (!monster.isDead()) {
            player.sendPacket(SystemMsg.THAT_IS_AN_INCORRECT_TARGET);
            return false;
        }
        final Skill skill = SkillTable.getInstance().getInfo(2098, 1);
        if (skill != null && skill.checkCondition(player, monster, false, false, true)) {
            player.getAI().Cast(skill, monster);
            return true;
        }
        return false;
    }
}
