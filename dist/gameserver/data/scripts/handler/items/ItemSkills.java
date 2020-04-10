package handler.items;

import gnu.trove.set.hash.TIntHashSet;
import ru.l2.gameserver.data.xml.holder.ItemTemplateHolder;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.templates.item.ItemTemplate;

public class ItemSkills extends ScriptItemHandler {
    private final int[] _itemIds;

    public ItemSkills() {
        final TIntHashSet set = new TIntHashSet();
        for (final ItemTemplate template : ItemTemplateHolder.getInstance().getAllTemplates()) {
            if (template != null) {
                for (final Skill skill : template.getAttachedSkills()) {
                    if (skill.isHandler()) {
                        set.add(template.getItemId());
                    }
                }
            }
        }
        _itemIds = set.toArray();
    }

    @Override
    public boolean useItem(final Playable playable, final ItemInstance item, final boolean ctrl) {
        Player player;
        if (playable.isPlayer()) {
            player = (Player) playable;
        } else {
            if (!playable.isPet()) {
                return false;
            }
            player = playable.getPlayer();
        }
        final Skill[] skills = item.getTemplate().getAttachedSkills();
        for (int i = 0; i < skills.length; ++i) {
            final Skill skill = skills[i];
            final Creature aimingTarget = skill.getAimingTarget(player, player.getTarget());
            if (skill.checkCondition(player, aimingTarget, ctrl, false, true)) {
                player.getAI().Cast(skill, aimingTarget, ctrl, false);
            } else if (i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int[] getItemIds() {
        return _itemIds;
    }
}
