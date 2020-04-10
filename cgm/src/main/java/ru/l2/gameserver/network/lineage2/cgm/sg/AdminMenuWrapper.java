package ru.l2.gameserver.network.lineage2.cgm.sg;

import ru.akumu.smartguard.core.AdminMenu;
import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.Player;

import java.util.Arrays;

public class AdminMenuWrapper implements IAdminCommandHandler {
    private static final AdminMenu MENU_INSTANCE = new AdminMenu();
    private static final AdminMenu.Commands[] MENU_COMMANS = AdminMenu.Commands.values();
    private static final AdminMenuWrapper INSTANCE = new AdminMenuWrapper();

    public static AdminMenuWrapper getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean useAdminCommand(final Enum comm, final String[] wordList, final String fullString, final Player activeChar) {
        return Arrays.stream(MENU_COMMANS).filter(e -> e == comm || comm.equals(e)).findFirst().filter(e -> MENU_INSTANCE.useAdminCommand(new PlayerWrapper(activeChar), e, wordList)).isPresent();
    }

    @Override
    public Enum[] getAdminCommandEnum() {
        return MENU_COMMANS;
    }
}