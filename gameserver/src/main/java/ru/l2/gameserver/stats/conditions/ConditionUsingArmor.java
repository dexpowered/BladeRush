package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.stats.Env;
import ru.l2.gameserver.templates.item.ArmorTemplate.ArmorType;

public class ConditionUsingArmor extends Condition {
    private final ArmorType _armor;

    public ConditionUsingArmor(final ArmorType armor) {
        _armor = armor;
    }

    @Override
    protected boolean testImpl(final Env env) {
        return env.character.isPlayer() && ((Player) env.character).isWearingArmor(_armor);
    }
}
