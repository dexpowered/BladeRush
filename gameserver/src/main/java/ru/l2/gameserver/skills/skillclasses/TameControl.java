package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.TamedBeastInstance;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class TameControl extends Skill {
    private final int _type;

    public TameControl(final StatsSet set) {
        super(set);
        _type = set.getInteger("type", 0);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (isSSPossible()) {
            activeChar.unChargeShots(isMagic());
        }
        if (!activeChar.isPlayer()) {
            return;
        }
        final Player player = activeChar.getPlayer();
        if (player.getTrainedBeast() == null) {
            return;
        }
        if (_type == 0) {
            for (final Creature target : targets) {
                if (target instanceof TamedBeastInstance && player.getTrainedBeast() == target) {
                    player.getTrainedBeast().despawnWithDelay(1000);
                }
            }
        } else if (_type > 0) {
            final TamedBeastInstance tamedBeast = player.getTrainedBeast();
            if (tamedBeast != null) {
                switch (_type) {
                    case 1: {
                        tamedBeast.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, player);
                        break;
                    }
                    case 3: {
                        tamedBeast.buffOwner();
                        break;
                    }
                    case 4: {
                        tamedBeast.doDespawn();
                        break;
                    }
                }
            }
        }
    }
}
