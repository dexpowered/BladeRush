package ru.l2.gameserver.handler.voicecommands.impl;

import com.stringer.annotations.HideAccess;
import com.stringer.annotations.StringEncryption;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.data.scripts.Functions;

@HideAccess
@StringEncryption
public class Online extends Functions implements IVoicedCommandHandler {
    private final String[] _commandList = {"online"};

    public Online() {
    }

    @Override
    public String[] getVoicedCommandList() {
        return _commandList;
    }

    @Override
    public boolean useVoicedCommand(final String command, final Player player, final String args) {
        if (Config.SERVICES_ONLINE_COMMAND_ENABLE || player.isGM()) {
            player.sendMessage(new CustomMessage("scripts.commands.user.online.service", player).addNumber(Math.round(GameObjectsStorage.getPlayers().size() * Config.SERVICE_COMMAND_MULTIPLIER)));
            return false;
        }
        return true;
    }
}
