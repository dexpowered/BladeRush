package ru.l2.gameserver.skills.skillclasses;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.instances.ChestInstance;
import ru.l2.gameserver.model.instances.DoorInstance;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class Unlock extends Skill {
    private final int _unlockPower;

    public Unlock(final StatsSet set) {
        super(set);
        _unlockPower = set.getInteger("unlockPower", 0) + 100;
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        if (target == null || (target instanceof ChestInstance && target.isDead())) {
            activeChar.sendPacket(Msg.INVALID_TARGET);
            return false;
        }
        if (target instanceof ChestInstance && activeChar.isPlayer()) {
            return super.checkCondition(activeChar, target, forceUse, dontMove, first);
        }
        if (!target.isDoor() || _unlockPower == 0) {
            activeChar.sendPacket(Msg.INVALID_TARGET);
            return false;
        }
        final DoorInstance door = (DoorInstance) target;
        if (door.isOpen()) {
            activeChar.sendPacket(Msg.IT_IS_NOT_LOCKED);
            return false;
        }
        if (!door.isUnlockable()) {
            activeChar.sendPacket(Msg.YOU_ARE_UNABLE_TO_UNLOCK_THE_DOOR);
            return false;
        }
        if (door.getKey() > 0) {
            activeChar.sendPacket(Msg.YOU_ARE_UNABLE_TO_UNLOCK_THE_DOOR);
            return false;
        }
        if (_unlockPower - door.getLevel() * 100 < 0) {
            activeChar.sendPacket(Msg.YOU_ARE_UNABLE_TO_UNLOCK_THE_DOOR);
            return false;
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        for (final Creature targ : targets) {
            if (targ != null) {
                if (targ.isDoor()) {
                    final DoorInstance target = (DoorInstance) targ;
                    if (!target.isOpen() && (target.getKey() > 0 || Rnd.chance(_unlockPower - target.getLevel() * 100))) {
                        target.openMe((Player) activeChar, true);
                    } else {
                        activeChar.sendPacket(Msg.YOU_HAVE_FAILED_TO_UNLOCK_THE_DOOR);
                    }
                } else {
                    if (!(targ instanceof ChestInstance)) {
                        continue;
                    }
                    final ChestInstance target2 = (ChestInstance) targ;
                    if (target2.isDead()) {
                        continue;
                    }
                    target2.tryOpen((Player) activeChar, this);
                }
            }
        }
    }
}
