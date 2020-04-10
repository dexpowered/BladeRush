package ru.l2.gameserver.model.items.listeners;

import ru.l2.gameserver.listener.inventory.OnEquipListener;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.Skill.SkillType;
import ru.l2.gameserver.model.items.ItemInstance;

public final class AccessoryListener implements OnEquipListener {
    private static final AccessoryListener _instance = new AccessoryListener();

    public static AccessoryListener getInstance() {
        return _instance;
    }

    @Override
    public void onUnequip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        final Player player = (Player) actor;
        if (item.getBodyPart() == 2097152 && item.getTemplate().getAttachedSkills().length > 0) {
            final int agathionId = player.getAgathionId();
            final int transformNpcId = player.getTransformationTemplate();
            for (final Skill skill : item.getTemplate().getAttachedSkills()) {
                if (agathionId > 0 && skill.getNpcId() == agathionId) {
                    player.setAgathion(0);
                }
                if (skill.getNpcId() == transformNpcId && skill.getSkillType() == SkillType.TRANSFORMATION) {
                    player.setTransformation(0);
                }
            }
        }
        if (item.isAccessory() || item.getTemplate().isTalisman()) {
            player.sendUserInfo(true);
        } else {
            player.broadcastCharInfo();
        }
    }

    @Override
    public void onEquip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        final Player player = (Player) actor;
        if (item.isAccessory() || item.getTemplate().isTalisman()) {
            player.sendUserInfo(true);
        } else {
            player.broadcastCharInfo();
        }
    }
}
