package ru.l2.gameserver.skills.skillclasses;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.templates.StatsSet;
import ru.l2.gameserver.utils.ItemFunctions;

import java.util.List;

public class SummonItem extends Skill {
    private final int _itemId;
    private final int _minId;
    private final int _maxId;
    private final long _minCount;
    private final long _maxCount;

    public SummonItem(final StatsSet set) {
        super(set);
        _itemId = set.getInteger("SummonItemId", 0);
        _minId = set.getInteger("SummonMinId", 0);
        _maxId = set.getInteger("SummonMaxId", _minId);
        _minCount = set.getLong("SummonMinCount");
        _maxCount = set.getLong("SummonMaxCount", _minCount);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        if (!activeChar.isPlayable()) {
            return;
        }
        for (final Creature target : targets) {
            if (target != null) {
                final int itemId = (_minId > 0) ? Rnd.get(_minId, _maxId) : _itemId;
                final long count = Rnd.get(_minCount, _maxCount);
                ItemFunctions.addItem((Playable) activeChar, itemId, count, true);
                getEffects(activeChar, target, getActivateRate() > 0, false);
            }
        }
    }
}
