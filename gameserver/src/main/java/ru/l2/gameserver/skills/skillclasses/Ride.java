package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class Ride extends Skill {
    public Ride(final StatsSet set) {
        super(set);
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        if (!activeChar.isPlayer()) {
            return false;
        }
        final Player player = (Player) activeChar;
        if (getNpcId() != 0) {
            if (player.isOlyParticipant()) {
                player.sendPacket(Msg.THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT);
                return false;
            }
            if (player.isInDuel() || player.isSitting() || player.isInCombat() || player.isFishing() || player.isCursedWeaponEquipped() || player.getTransformation() != 0 || player.getPet() != null || player.isMounted() || player.isInBoat()) {
                player.sendPacket(Msg.YOU_CANNOT_MOUNT_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS);
                return false;
            }
        } else if (getNpcId() == 0 && !player.isMounted()) {
            return false;
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature caster, final List<Creature> targets) {
        if (!caster.isPlayer()) {
            return;
        }
        final Player activeChar = (Player) caster;
        activeChar.setMount(getNpcId(), 0, 0);
    }
}
