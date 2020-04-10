package ru.l2.gameserver.model.items.listeners;

import ru.l2.gameserver.data.xml.holder.OptionDataHolder;
import ru.l2.gameserver.listener.inventory.OnEquipListener;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.stats.triggers.TriggerInfo;
import ru.l2.gameserver.templates.OptionDataTemplate;

public final class ItemEnchantOptionsListener implements OnEquipListener {
    private static final ItemEnchantOptionsListener _instance = new ItemEnchantOptionsListener();

    public static ItemEnchantOptionsListener getInstance() {
        return _instance;
    }

    @Override
    public void onEquip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        final Player player = actor.getPlayer();
        boolean needSendInfo = false;
        for (final int i : item.getEnchantOptions()) {
            final OptionDataTemplate template = OptionDataHolder.getInstance().getTemplate(i);
            if (template != null) {
                player.addStatFuncs(template.getStatFuncs(template));
                for (final Skill skill : template.getSkills()) {
                    player.addSkill(skill, false);
                    needSendInfo = true;
                }
                for (final TriggerInfo triggerInfo : template.getTriggerList()) {
                    player.addTrigger(triggerInfo);
                }
            }
        }
        if (needSendInfo) {
            player.sendSkillList();
        }
        player.sendChanges();
    }

    @Override
    public void onUnequip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        final Player player = actor.getPlayer();
        boolean needSendInfo = false;
        for (final int i : item.getEnchantOptions()) {
            final OptionDataTemplate template = OptionDataHolder.getInstance().getTemplate(i);
            if (template != null) {
                player.removeStatsOwner(template);
                for (final Skill skill : template.getSkills()) {
                    player.removeSkill(skill, false);
                    needSendInfo = true;
                }
                for (final TriggerInfo triggerInfo : template.getTriggerList()) {
                    player.removeTrigger(triggerInfo);
                }
            }
        }
        if (needSendInfo) {
            player.sendSkillList();
        }
        player.sendChanges();
    }
}
