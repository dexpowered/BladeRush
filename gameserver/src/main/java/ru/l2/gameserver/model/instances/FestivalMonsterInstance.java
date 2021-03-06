package ru.l2.gameserver.model.instances;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.model.reward.RewardList;
import ru.l2.gameserver.model.reward.RewardType;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage2;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.ItemFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class FestivalMonsterInstance extends MonsterInstance {
    protected int _bonusMultiplier;

    public FestivalMonsterInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
        _bonusMultiplier = 1;
        _hasRandomWalk = false;
    }

    public void setOfferingBonus(final int bonusMultiplier) {
        _bonusMultiplier = bonusMultiplier;
    }

    @Override
    protected void onSpawn() {
        super.onSpawn();
        final List<Player> pl = World.getAroundPlayers(this);
        if (pl.isEmpty()) {
            return;
        }
        final List<Player> alive = new ArrayList<>(9);
        for (final Player p : pl) {
            if (!p.isDead()) {
                alive.add(p);
            }
        }
        if (alive.isEmpty()) {
            return;
        }
        final Player target = alive.get(Rnd.get(alive.size()));
        getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, target, 1);
    }

    @Override
    public void rollRewards(final Entry<RewardType, RewardList> entry, final Creature lastAttacker, final Creature topDamager) {
        super.rollRewards(entry, lastAttacker, topDamager);
        if (entry.getKey() != RewardType.RATED_GROUPED) {
            return;
        }
        if (!topDamager.isPlayable()) {
            return;
        }
        final Player topDamagerPlayer = topDamager.getPlayer();
        final Party associatedParty = topDamagerPlayer.getParty();
        if (associatedParty == null) {
            return;
        }
        final Player partyLeader = associatedParty.getPartyLeader();
        if (partyLeader == null) {
            return;
        }
        final ItemInstance bloodOfferings = ItemFunctions.createItem(5901);
        bloodOfferings.setCount(_bonusMultiplier);
        partyLeader.getInventory().addItem(bloodOfferings);
        partyLeader.sendPacket(SystemMessage2.obtainItems(5901, _bonusMultiplier, 0));
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public int getAggroRange() {
        return 1000;
    }

    @Override
    public boolean hasRandomAnimation() {
        return false;
    }

    @Override
    public boolean canChampion() {
        return false;
    }
}
