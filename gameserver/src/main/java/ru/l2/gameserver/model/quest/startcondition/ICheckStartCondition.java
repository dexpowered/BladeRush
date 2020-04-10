package ru.l2.gameserver.model.quest.startcondition;


import ru.l2.gameserver.model.Player;

@FunctionalInterface
public interface ICheckStartCondition {
    ConditionList checkCondition(Player player);
}