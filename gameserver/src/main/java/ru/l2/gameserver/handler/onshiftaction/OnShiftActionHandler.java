package ru.l2.gameserver.handler.onshiftaction;


import ru.l2.gameserver.model.Player;

/**
 * @author VISTALL
 * @date 2:38/19.08.2011
 */
@FunctionalInterface
public interface OnShiftActionHandler<T> {
    boolean call(T t, Player player);
}
