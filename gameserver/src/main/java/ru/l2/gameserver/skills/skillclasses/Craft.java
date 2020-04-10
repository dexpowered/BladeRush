package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.network.lineage2.serverpackets.RecipeBookItemList;
import ru.l2.gameserver.templates.StatsSet;

import java.util.List;

public class Craft extends Skill {
    private final boolean _dwarven;

    public Craft(final StatsSet set) {
        super(set);
        _dwarven = set.getBool("isDwarven");
    }

    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        final Player p = (Player) activeChar;
        return !p.isInStoreMode() && !p.isProcessingRequest() && super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature activeChar, final List<Creature> targets) {
        activeChar.sendPacket(new RecipeBookItemList((Player) activeChar, _dwarven));
    }
}
