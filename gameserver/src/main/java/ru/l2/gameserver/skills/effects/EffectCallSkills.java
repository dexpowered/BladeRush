package ru.l2.gameserver.skills.effects;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Effect;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.network.lineage2.serverpackets.MagicSkillUse;
import ru.l2.gameserver.stats.Env;
import ru.l2.gameserver.tables.SkillTable;

public class EffectCallSkills extends Effect {
    public EffectCallSkills(final Env env, final EffectTemplate template) {
        super(env, template);
    }

    @Override
    public void onStart() {
        super.onStart();
        final int[] skillIds = getTemplate().getParam().getIntegerArray("skillIds");
        final int[] skillLevels = getTemplate().getParam().getIntegerArray("skillLevels");
        for (int i = 0; i < skillIds.length; ++i) {
            final Skill skill = SkillTable.getInstance().getInfo(skillIds[i], skillLevels[i]);
            for (final Creature cha : skill.getTargets(getEffector(), getEffected(), false)) {
                getEffector().broadcastPacket(new MagicSkillUse(getEffector(), cha, skillIds[i], skillLevels[i], 0, 0L));
            }
            getEffector().callSkill(skill, skill.getTargets(getEffector(), getEffected(), false), false);
        }
    }

    @Override
    public boolean onActionTime() {
        return false;
    }
}
