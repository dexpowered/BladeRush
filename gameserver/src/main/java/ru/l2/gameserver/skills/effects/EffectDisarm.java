package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.stats.Env;

public final class EffectDisarm extends Effect {
    public EffectDisarm(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public boolean checkCondition() {
        if (!_effected.isPlayer()) {
            return false;
        }
        final Player player = _effected.getPlayer();
        return !player.isCursedWeaponEquipped() && player.getActiveWeaponFlagAttachment() == null && super.checkCondition();
    }

    @Override
    public void onStart() {
        super.onStart();
        final Player player = (Player) _effected;
        final ItemInstance wpn = player.getActiveWeaponInstance();
        if (wpn != null) {
            player.getInventory().unEquipItem(wpn);
            player.sendDisarmMessage(wpn);
        }
        player.startWeaponEquipBlocked();
    }

    @Override
    public void onExit() {
        super.onExit();
        _effected.stopWeaponEquipBlocked();
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
