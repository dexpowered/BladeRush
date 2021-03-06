package ru.l2.gameserver.network.lineage2.clientpackets;

import org.apache.commons.lang3.ArrayUtils;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.PetInstance;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.utils.ItemFunctions;

public class RequestPetUseItem extends L2GameClientPacket {
    private int _objectId;

    @Override
    protected void readImpl() {
        _objectId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (activeChar.isActionsDisabled()) {
            activeChar.sendActionFailed();
            return;
        }
        if (activeChar.isFishing()) {
            activeChar.sendPacket(Msg.YOU_CANNOT_DO_THAT_WHILE_FISHING);
            return;
        }
        activeChar.setActive();
        final PetInstance pet = (PetInstance) activeChar.getPet();
        if (pet == null) {
            return;
        }
        final ItemInstance item = pet.getInventory().getItemByObjectId(_objectId);
        if (item == null || item.getCount() < 1L) {
            return;
        }
        if (activeChar.isAlikeDead() || pet.isDead() || pet.isOutOfControl()) {
            activeChar.sendPacket(new SystemMessage(113).addItemName(item.getItemId()));
            return;
        }
        if (pet.tryFeedItem(item)) {
            return;
        }
        if (ArrayUtils.contains(Config.ALT_ALLOWED_PET_POTIONS, item.getItemId())) {
            final Skill[] skills = item.getTemplate().getAttachedSkills();
            if (skills.length > 0) {
                for (final Skill skill : skills) {
                    final Creature aimingTarget = skill.getAimingTarget(pet, pet.getTarget());
                    if (skill.checkCondition(pet, aimingTarget, false, false, true)) {
                        pet.getAI().Cast(skill, aimingTarget, false, false);
                    }
                }
            }
            return;
        }
        final SystemMessage sm = ItemFunctions.checkIfCanEquip(pet, item);
        if (sm == null) {
            if (item.isEquipped()) {
                pet.getInventory().unEquipItem(item);
            } else {
                pet.getInventory().equipItem(item);
            }
            pet.broadcastCharInfo();
            return;
        }
        activeChar.sendPacket(sm);
    }
}
