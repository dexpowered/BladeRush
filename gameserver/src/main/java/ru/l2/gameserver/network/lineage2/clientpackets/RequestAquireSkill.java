package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.commons.lang.ArrayUtils;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.xml.holder.SkillAcquireHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.SkillLearn;
import ru.l2.gameserver.model.base.AcquireType;
import ru.l2.gameserver.model.base.ClassId;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.model.instances.VillageMasterInstance;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage2;
import ru.l2.gameserver.tables.SkillTable;
import ru.l2.gameserver.utils.ItemFunctions;

public class RequestAquireSkill extends L2GameClientPacket {
    private AcquireType _type;
    private int _id;
    private int _level;

    private static void learnSimpleNextLevel(final Player player, final SkillLearn skillLearn, final Skill skill) {
        final int skillLevel = player.getSkillLevel(skillLearn.getId(), 0);
        if (skillLevel != skillLearn.getLevel() - 1) {
            return;
        }
        learnSimple(player, skillLearn, skill);
    }

    private static void learnSimpleNextFishingLevel(final Player player, final SkillLearn skillLearn, final Skill skill) {
        final int skillLevel = player.getSkillLevel(skillLearn.getId(), 0);
        if (skillLevel != skillLearn.getLevel() - 1) {
            return;
        }
        learnSimpleFishing(player, skillLearn, skill);
    }

    private static void learnSimple(final Player player, final SkillLearn skillLearn, final Skill skill) {
        int count_item_learn = Config.ALT_ITEM_LEARN_COUNT;
        int item_learn = Config.ALT_ITEM_LEARN_ID;



        if(Config.ALT_ITEM_LEARN_SP)
        {
            if(skillLearn.getCost() == 0){
                count_item_learn = 1;
            }else
                count_item_learn = skillLearn.getCost();
        }


        for (Config.AltSkillPrice config : Config.ALT_PRICE_SKILL) {

            if(skill.getId() == config.skillId)
            {
                item_learn = config.priceId;
                count_item_learn = config.priceCount;
            }
        }

        if (player.getSp() < skillLearn.getCost()) {
            player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_SP_TO_LEARN_THIS_SKILL);
            return;
        }

        if (player.getInventory().getItemByItemId(item_learn) == null || player.getInventory().getItemByItemId(item_learn).getCount() < count_item_learn)
        {
            player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_THE_NECESSARY_MATERIALS_OR_PREREQUISITES_TO_LEARN_THIS_SKILL);
            return;
        }

        ItemFunctions.removeItem(player, item_learn, count_item_learn,  true);

        player.sendPacket((new SystemMessage2(SystemMsg.YOU_HAVE_EARNED_S1_SKILL)).addSkillName(skill.getId(), skill.getLevel()));
        player.setSp(player.getSp() - skillLearn.getCost());
        player.addSkill(skill, true);
        player.sendUserInfo();
        player.updateStats();
        player.sendSkillList();
        RequestExEnchantSkill.updateSkillShortcuts(player, skill.getId(), skill.getLevel());
    }

    private static void learnSimpleFishing(final Player player, final SkillLearn skillLearn, final Skill skill) {
        if (player.getSp() < skillLearn.getCost()) {
            player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_SP_TO_LEARN_THIS_SKILL);
            return;
        }
        if (skillLearn.getItemId() > 0 && !player.consumeItem(skillLearn.getItemId(), skillLearn.getItemCount())) {
            player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_THE_NECESSARY_MATERIALS_OR_PREREQUISITES_TO_LEARN_THIS_SKILL);
            return;
        }
        player.sendPacket((new SystemMessage2(SystemMsg.YOU_HAVE_EARNED_S1_SKILL)).addSkillName(skill.getId(), skill.getLevel()));
        player.setSp(player.getSp() - skillLearn.getCost());
        player.addSkill(skill, true);
        player.sendUserInfo();
        player.updateStats();
        player.sendSkillList();
        RequestExEnchantSkill.updateSkillShortcuts(player, skill.getId(), skill.getLevel());
    }

    private static void learnClanSkill(final Player player, final SkillLearn skillLearn, final NpcInstance trainer, final Skill skill) {
        if (!(trainer instanceof VillageMasterInstance)) {
            return;
        }
        if (!player.isClanLeader()) {
            player.sendPacket(SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            return;
        }
        final Clan clan = player.getClan();
        final int skillLevel = clan.getSkillLevel(skillLearn.getId(), 0);
        if (skillLevel != skillLearn.getLevel() - 1) {
            return;
        }
        if (clan.getReputationScore() < skillLearn.getCost()) {
            player.sendPacket(SystemMsg.THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW);
            return;
        }
        if (skillLearn.getItemId() != 0 && !player.consumeItem(skillLearn.getItemId(), skillLearn.getItemCount())) {
            player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_THE_NECESSARY_MATERIALS_OR_PREREQUISITES_TO_LEARN_THIS_SKILL);
            return;
        }
        clan.incReputation(-skillLearn.getCost(), false, "AquireSkill: " + skillLearn.getId() + ", lvl " + skillLearn.getLevel());
        clan.addSkill(skill, true);
        clan.broadcastToOnlineMembers((new SystemMessage2(SystemMsg.THE_CLAN_SKILL_S1_HAS_BEEN_ADDED)).addSkillName(skill));
        NpcInstance.showClanSkillList(player);
    }

    private static boolean checkSpellbook(final Player player, final SkillLearn skillLearn) {
        return Config.ALT_DISABLE_SPELLBOOKS || skillLearn.getItemId() == 0 || (!skillLearn.isClicked() && player.getInventory().getCountOf(skillLearn.getItemId()) >= skillLearn.getItemCount());
    }

    @Override
    protected void readImpl() {
        _id = readD();
        _level = readD();
        _type = ArrayUtils.valid(AcquireType.VALUES, readD());
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null || player.getTransformation() != 0 || _type == null) {
            return;
        }
        final NpcInstance trainer = player.getLastNpc();
        if (trainer == null || !trainer.isInActingRange(player)) {
            return;
        }
        final Skill skill = SkillTable.getInstance().getInfo(_id, _level);
        if (skill == null) {
            return;
        }
        final int clsId = player.getVarInt("AcquireSkillClassId", player.getClassId().getId());
        final ClassId classId = (clsId >= 0 && clsId < ClassId.VALUES.length) ? ClassId.VALUES[clsId] : null;
        if (!SkillAcquireHolder.getInstance().isSkillPossible(player, classId, skill, _type)) {
            return;
        }
        final SkillLearn skillLearn = SkillAcquireHolder.getInstance().getSkillLearn(player, classId, _id, _level, _type);
        if (skillLearn == null) {
            return;
        }
        if (!checkSpellbook(player, skillLearn)) {
            player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_THE_NECESSARY_MATERIALS_OR_PREREQUISITES_TO_LEARN_THIS_SKILL);
            return;
        }
        switch (_type) {
            case NORMAL: {
                learnSimpleNextLevel(player, skillLearn, skill);
                if (trainer != null) {
                    trainer.showSkillList(player, classId);
                    break;
                }
                break;
            }
            case FISHING: {
                learnSimpleNextFishingLevel(player, skillLearn, skill);
                if (trainer != null) {
                    NpcInstance.showFishingSkillList(player);
                    break;
                }
                break;
            }
            case CLAN: {
                learnClanSkill(player, skillLearn, trainer, skill);
                break;
            }
        }
    }
}
