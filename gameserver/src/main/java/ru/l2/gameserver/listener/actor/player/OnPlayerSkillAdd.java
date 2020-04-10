package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;

/**
 * Created by JunkyFunky
 * on 20.12.2017 10:07
 * group j2dev
 */
@FunctionalInterface
public interface OnPlayerSkillAdd extends PlayerListener {
    void onPlayerSkillAdd(Player player, Skill newSkill, Skill oldSkill);
}
