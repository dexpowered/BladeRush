package ru.l2.gameserver.model.items.listeners;

import ru.l2.gameserver.data.xml.holder.OptionDataHolder;
import ru.l2.gameserver.listener.inventory.OnEquipListener;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.SkillCoolTime;
import ru.l2.gameserver.templates.OptionDataTemplate;

public final class ItemAugmentationListener implements OnEquipListener {
    private static final ItemAugmentationListener _instance = new ItemAugmentationListener();

    public static ItemAugmentationListener getInstance() {
        return _instance;
    }

    @Override
    public void onUnequip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        if (!item.isAugmented()) {
            return;
        }
        final Player player = actor.getPlayer();
        final int[] stats = {item.getVariationStat1(), item.getVariationStat2()};
        boolean sendList = false;
        for (final int i : stats) {
            final OptionDataTemplate template = OptionDataHolder.getInstance().getTemplate(i);
            if (template != null) {
                player.removeStatsOwner(template);
                for (final Skill skill : template.getSkills()) {
                    if(actor.isCastingNow() && actor.getCastingSkill() == skill) {
                        actor.abortCast(true, true);
                    }
                    sendList = true;
                    player.removeSkill(skill);
                }
                player.removeTriggers(template);
            }
        }
        if (sendList) {
            player.sendSkillList();
        }
        player.updateStats();
    }

    @Override
    public void onEquip(final int slot, final ItemInstance item, final Playable actor) {
        if (!item.isEquipable()) {
            return;
        }
        if (!item.isAugmented()) {
            return;
        }
        final Player player = actor.getPlayer();
        if (player.getExpertisePenalty(item) > 0) {
            return;
        }
        final int[] stats = {item.getVariationStat1(), item.getVariationStat2()};
        boolean sendList = false;
        boolean sendReuseList = false;
        for (final int i : stats) {
            final OptionDataTemplate template = OptionDataHolder.getInstance().getTemplate(i);
            if (template != null) {
                player.addStatFuncs(template.getStatFuncs(template));
                for (final Skill skill : template.getSkills()) {
                    sendList = true;
                    player.addSkill(skill);
                    if (player.isSkillDisabled(skill)) {
                        sendReuseList = true;
                    }
                }
                player.addTriggers(template);
            }
        }
        if (sendList) {
            player.sendSkillList();
        }
        if (sendReuseList) {
            player.sendPacket(new SkillCoolTime(player));
        }
        player.updateStats();
    }
}
