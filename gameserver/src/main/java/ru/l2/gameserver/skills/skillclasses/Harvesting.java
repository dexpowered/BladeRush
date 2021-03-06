package ru.l2.gameserver.skills.skillclasses;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.model.reward.RewardItem;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.templates.StatsSet;
import ru.l2.gameserver.utils.ItemFunctions;

import java.util.List;

public class Harvesting extends Skill {
    public Harvesting(final StatsSet set) {
        super(set);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (!activeChar.isPlayer()) {
            return;
        }
        final Player player = (Player) activeChar;
        for (final Creature target : targets) {
            if (target != null) {
                if (!target.isMonster()) {
                    continue;
                }
                final MonsterInstance monster = (MonsterInstance) target;
                if (!monster.isSeeded()) {
                    activeChar.sendPacket(Msg.THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN);
                } else if (!monster.isSeeded(player)) {
                    activeChar.sendPacket(Msg.YOU_ARE_NOT_AUTHORIZED_TO_HARVEST);
                } else {
                    double SuccessRate = Config.MANOR_HARVESTING_BASIC_SUCCESS;
                    final int diffPlayerTarget = Math.abs(activeChar.getLevel() - monster.getLevel());
                    if (diffPlayerTarget > Config.MANOR_DIFF_PLAYER_TARGET) {
                        SuccessRate -= (diffPlayerTarget - Config.MANOR_DIFF_PLAYER_TARGET) * Config.MANOR_DIFF_PLAYER_TARGET_PENALTY;
                    }
                    if (SuccessRate < 1.0) {
                        SuccessRate = 1.0;
                    }
                    if (player.isGM()) {
                        player.sendMessage(new CustomMessage("l2p.gameserver.skills.skillclasses.Harvesting.Chance", player).addNumber((long) SuccessRate));
                    }
                    if (!Rnd.chance(SuccessRate)) {
                        activeChar.sendPacket(Msg.THE_HARVEST_HAS_FAILED);
                        monster.clearHarvest();
                    } else {
                        final RewardItem item = monster.takeHarvest();
                        if (item == null) {
                            continue;
                        }
                        if (!player.getInventory().validateCapacity(item.itemId, item.count) || !player.getInventory().validateWeight(item.itemId, item.count)) {
                            final ItemInstance harvest = ItemFunctions.createItem(item.itemId);
                            harvest.setCount(item.count);
                            harvest.dropToTheGround(player, monster);
                        } else {
                            player.getInventory().addItem(item.itemId, (long) (item.count * Config.MANOR_HARVESTING_REWARD_RATE));
                            player.sendPacket(new SystemMessage(1137).addName(player).addNumber((long) (item.count * Config.MANOR_HARVESTING_REWARD_RATE)).addItemName(item.itemId));
                            if (!player.isInParty()) {
                                continue;
                            }
                            final SystemMessage smsg = new SystemMessage(1137).addString(player.getName()).addNumber((long) (item.count * Config.MANOR_HARVESTING_REWARD_RATE)).addItemName(item.itemId);
                            player.getParty().broadcastToPartyMembers(player, smsg);
                        }
                    }
                }
            }
        }
    }
}
