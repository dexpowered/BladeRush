package ru.l2.gameserver.utils;

import ru.l2.gameserver.data.xml.holder.ResidenceHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.residence.Residence;
import ru.l2.gameserver.tables.SkillTable;

public class SiegeUtils {
    public static void addSiegeSkills(final Player character) {
        character.addSkill(SkillTable.getInstance().getInfo(246, 1), false);
        character.addSkill(SkillTable.getInstance().getInfo(247, 1), false);
        if (character.isNoble()) {
            character.addSkill(SkillTable.getInstance().getInfo(326, 1), false);
        }
    }

    public static void removeSiegeSkills(final Player character) {
        character.removeSkill(SkillTable.getInstance().getInfo(246, 1), false);
        character.removeSkill(SkillTable.getInstance().getInfo(247, 1), false);
        character.removeSkill(SkillTable.getInstance().getInfo(326, 1), false);
    }

    public static boolean getCanRide() {
        for (final Residence residence : ResidenceHolder.getInstance().getResidences()) {
            if (residence != null && residence.getSiegeEvent().isInProgress()) {
                return false;
            }
        }
        return true;
    }
}
