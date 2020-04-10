package handler.usercommands;

import ru.l2.gameserver.handler.usercommands.IUserCommandHandler;
import ru.l2.gameserver.handler.usercommands.UserCommandHandler;
import ru.l2.gameserver.listener.script.OnInitScriptListener;

public abstract class ScriptUserCommand implements IUserCommandHandler, OnInitScriptListener {
    @Override
    public void onInit() {
        UserCommandHandler.getInstance().registerUserCommandHandler(this);
    }

}
