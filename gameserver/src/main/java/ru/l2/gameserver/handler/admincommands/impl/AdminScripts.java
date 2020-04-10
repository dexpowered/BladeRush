package ru.l2.gameserver.handler.admincommands.impl;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.quest.Quest;
import ru.l2.gameserver.model.quest.QuestState;
import ru.l2.gameserver.scripts.Scripts;

import java.io.File;
import java.util.List;

public class AdminScripts implements IAdminCommandHandler {

    @Override
    public boolean useAdminCommand(final Enum<?> comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands command = (Commands) comm;

        if (!activeChar.isGM()) {
            return false;
        }

        switch (command) {
            case admin_run_script:
            case admin_runs:
                if (wordList.length < 2)
                    return false;
                String param = wordList[1];
                if (!run(param))
                    activeChar.sendMessage("Can't run script.");
                else
                    activeChar.sendMessage("Running script...");
                break;
            case admin_sqreload:
                reloadQuestStates(activeChar);
                break;
        }
        return true;
    }

    private boolean run(String target) {
        final File file = new File(Config.DATAPACK_ROOT, "data/scripts/" + target.replace(".", "/") + ".java");
        if (!file.exists()) {
            return false;
        }

        final List<Class<?>> classes = Scripts.getInstance().load(file);
        for (Class<?> clazz : classes) {
            if (!clazz.isAssignableFrom(Runnable.class)) {
                return false;
            }

            final Runnable r;
            try {
                r = (Runnable) clazz.newInstance();
            } catch (Exception e) {
                return false;
            }

            ThreadPoolManager.getInstance().execute(r);
            return true;
        }

        return false;
    }

    private void reloadQuestStates(final Player p) {
        for (final QuestState qs : p.getAllQuestsStates()) {
            p.removeQuestState(qs.getQuest().getName());
        }

        Quest.restoreQuestStates(p);
    }

    @Override
    public Enum[] getAdminCommandEnum() {
        return Commands.values();
    }

    private enum Commands {

        admin_run_script, admin_runs, admin_sqreload
    }
}
