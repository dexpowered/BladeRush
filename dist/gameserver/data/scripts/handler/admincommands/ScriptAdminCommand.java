package handler.admincommands;

import ru.l2.gameserver.handler.admincommands.AdminCommandHandler;
import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.listener.script.OnInitScriptListener;

public abstract class ScriptAdminCommand implements IAdminCommandHandler, OnInitScriptListener {
    @Override
    public void onInit() {
        AdminCommandHandler.getInstance().registerAdminCommandHandler(this);
    }

}
