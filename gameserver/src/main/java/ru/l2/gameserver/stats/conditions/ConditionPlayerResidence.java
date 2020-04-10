package ru.l2.gameserver.stats.conditions;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.residence.ResidenceType;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.stats.Env;

public class ConditionPlayerResidence extends Condition {
    private final int _id;
    private final ResidenceType _type;

    public ConditionPlayerResidence(final int id, final ResidenceType type) {
        _id = id;
        _type = type;
    }

    @Override
    protected boolean testImpl(final Env env) {
        if (!env.character.isPlayer()) {
            return false;
        }
        final Player player = (Player) env.character;
        final Clan clan = player.getClan();
        if (clan == null) {
            return false;
        }
        final int residenceId = clan.getResidenceId(_type);
        return (_id > 0) ? (residenceId == _id) : (residenceId > 0);
    }
}
