package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.model.reward.RewardItem;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.templates.StatsSet;
import ru.l2.gameserver.utils.ItemFunctions;

import java.util.List;

public class Sweep extends Skill {
    public Sweep(final StatsSet set) {
        super(set);
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        if (isNotTargetAoE()) {
            return super.checkCondition(activeChar, target, forceUse, dontMove, first);
        }
        if (target == null) {
            return false;
        }
        if (!target.isMonster() || !target.isDead()) {
            activeChar.sendPacket(Msg.INVALID_TARGET);
            return false;
        }
        if (!((MonsterInstance) target).isSpoiled()) {
            activeChar.sendPacket(Msg.SWEEPER_FAILED_TARGET_NOT_SPOILED);
            return false;
        }
        if (!((MonsterInstance) target).isSpoiled((Player) activeChar)) {
            activeChar.sendPacket(Msg.THERE_ARE_NO_PRIORITY_RIGHTS_ON_A_SWEEPER);
            return false;
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (!activeChar.isPlayer()) {
            return;
        }
        final Player player = (Player) activeChar;
        for (final Creature targ : targets) {
            if (targ != null && targ.isMonster() && targ.isDead()) {
                if (!((MonsterInstance) targ).isSpoiled()) {
                    continue;
                }
                final MonsterInstance target = (MonsterInstance) targ;
                if (!target.isSpoiled(player)) {
                    activeChar.sendPacket(Msg.THERE_ARE_NO_PRIORITY_RIGHTS_ON_A_SWEEPER);
                } else {
                    final List<RewardItem> items = target.takeSweep();
                    if (items == null) {
                        activeChar.getAI().setAttackTarget(null);
                        target.endDecayTask();
                    } else {
                        for (final RewardItem item : items) {
                            final ItemInstance sweep = ItemFunctions.createItem(item.itemId);
                            sweep.setCount(item.count);
                            if (player.isInParty() && player.getParty().isDistributeSpoilLoot()) {
                                player.getParty().distributeItem(player, sweep, null);
                            } else if (!player.getInventory().validateCapacity(sweep) || !player.getInventory().validateWeight(sweep)) {
                                sweep.dropToTheGround(player, target);
                            } else {
                                player.getInventory().addItem(sweep);
                                if (item.count == 1L) {
                                    final SystemMessage smsg = new SystemMessage(30);
                                    smsg.addItemName(item.itemId);
                                    player.sendPacket(smsg);
                                } else {
                                    final SystemMessage smsg = new SystemMessage(29);
                                    smsg.addItemName(item.itemId);
                                    smsg.addNumber(item.count);
                                    player.sendPacket(smsg);
                                }
                                if (!player.isInParty()) {
                                    continue;
                                }
                                if (item.count == 1L) {
                                    final SystemMessage smsg = new SystemMessage(609);
                                    smsg.addName(player);
                                    smsg.addItemName(item.itemId);
                                    player.getParty().broadcastToPartyMembers(player, smsg);
                                } else {
                                    final SystemMessage smsg = new SystemMessage(608);
                                    smsg.addName(player);
                                    smsg.addItemName(item.itemId);
                                    smsg.addNumber(item.count);
                                    player.getParty().broadcastToPartyMembers(player, smsg);
                                }
                            }
                        }
                        activeChar.getAI().setAttackTarget(null);
                        target.endDecayTask();
                    }
                }
            }
        }
    }
}
