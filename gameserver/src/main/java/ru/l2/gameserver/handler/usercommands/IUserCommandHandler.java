package ru.l2.gameserver.handler.usercommands;

import ru.l2.gameserver.model.Player;

public interface IUserCommandHandler {
    boolean useUserCommand(final int p0, final Player p1);

    int[] getUserCommandList();
}
