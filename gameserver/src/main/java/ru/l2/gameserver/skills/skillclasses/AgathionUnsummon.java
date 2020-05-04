package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class AgathionUnsummon extends Skill {

    public AgathionUnsummon(final StatsSet set) {
        super(set);
    }
    @Override
    public void useSkill(final Creature caster, final List<Creature> targets) {
        final Player player = caster.getPlayer();

        if(player.getAgathion() != null)
        {
            player.removeSkill(this, true);
            player.sendSkillList();

            player.getAgathion().doDespawn();
            if(Config.DEBUG) {
                player.sendDebugMessage("Success: #doDespawn");
            }
        }

        if (isSSPossible()) {
            caster.unChargeShots(isMagic());
        }
    }
}
