package ru.l2.gameserver.stats.triggers;

import ru.l2.commons.lang.ArrayUtils;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.Skill.AddedSkill;
import ru.l2.gameserver.stats.Env;
import ru.l2.gameserver.stats.conditions.Condition;

public class TriggerInfo extends AddedSkill {
    private final TriggerType _type;
    private final double _chance;
    private Condition[] _conditions;

    public TriggerInfo(final int id, final int level, final TriggerType type, final double chance) {
        super(id, level);
        _conditions = Condition.EMPTY_ARRAY;
        _type = type;
        _chance = chance;
    }

    public final void addCondition(final Condition c) {
        _conditions = ArrayUtils.add(_conditions, c);
    }

    public boolean checkCondition(final Creature actor, final Creature target, final Creature aimTarget, final Skill owner, final double damage) {
        if (getSkill().checkTarget(actor, aimTarget, aimTarget, false, false) != null) {
            return false;
        }
        final Env env = new Env();
        env.character = actor;
        env.skill = owner;
        env.target = target;
        env.value = damage;
        for (final Condition c : _conditions) {
            if (!c.test(env)) {
                return false;
            }
        }
        return true;
    }

    public TriggerType getType() {
        return _type;
    }

    public double getChance() {
        return _chance;
    }
}
