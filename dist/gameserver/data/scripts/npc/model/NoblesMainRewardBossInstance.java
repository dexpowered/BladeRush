package npc.model;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.olympiad.NoblessManager;
import ru.l2.gameserver.model.instances.BossInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.SkillList;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class NoblesMainRewardBossInstance extends BossInstance {
    public NoblesMainRewardBossInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    protected void onDeath(final Creature killer) {
        super.onDeath(killer);
        if (killer != null && killer.isPlayable()) {
            final Player player = killer.getPlayer();
            if (player.isDead() || player.getParty() == null) {
                return;
            }
            final Party playerParty = player.getParty();
            for (final Player partyPlayer : playerParty) {
                if (partyPlayer != null && !partyPlayer.isDead() && !partyPlayer.isNoble() && !partyPlayer.isSubClassActive() && partyPlayer.getLevel() >= 76) {
                    if (getDistance3D(partyPlayer) > Config.ALT_PARTY_DISTRIBUTION_RANGE) {
                        continue;
                    }
                    NoblessManager.getInstance().addNoble(partyPlayer.getPlayer());
                    partyPlayer.setNoble(true);
                    partyPlayer.updatePledgeClass();
                    partyPlayer.updateNobleSkills();
                    partyPlayer.getPlayer().sendPacket(new SkillList(partyPlayer.getPlayer()));
                    partyPlayer.getPlayer().broadcastUserInfo(true);
                }
            }
        }
    }
}
