package ru.l2.gameserver.handler.voicecommands.impl;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;

public class Debug implements IVoicedCommandHandler {
    private final String[] _commandList = {"debug"};

    public Debug() {
    }

    @Override
    public String[] getVoicedCommandList() {
        return _commandList;
    }

    @Override
    public boolean useVoicedCommand(final String command, final Player player, final String args) {
        if (!Config.ALT_DEBUG_ENABLED) {
            return false;
        }
        if (player.isDebug()) {
            player.setDebug(false);
            player.sendMessage(new CustomMessage("voicedcommandhandlers.Debug.Disabled", player));
        } else {
            player.setDebug(true);
            player.sendMessage(new CustomMessage("voicedcommandhandlers.Debug.Enabled", player));
        }
        return true;
    }
}
