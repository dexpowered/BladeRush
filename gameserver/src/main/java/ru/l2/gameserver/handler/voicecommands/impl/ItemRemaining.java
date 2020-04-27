package ru.l2.gameserver.handler.voicecommands.impl;

import org.apache.commons.lang3.ArrayUtils;
import ru.l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.data.scripts.Functions;
import ru.l2.gameserver.utils.Util;

import java.util.List;

public class ItemRemaining extends Functions implements IVoicedCommandHandler {
    private final String[] _commandList = {"itemremaining", "itemsremaining", "rune", "rune_remaining", "runeremaining"};

    public ItemRemaining() {
    }

    @Override
    public boolean useVoicedCommand(final String command, final Player activeChar, final String target) {
        if (ArrayUtils.contains(_commandList, command.toLowerCase())) {
            sendItemRemaining(activeChar);
            return true;
        }
        return false;
    }

    @Override
    public String[] getVoicedCommandList() {
        return _commandList;
    }

    private void sendItemRemaining(final Player player) {
        final List<ItemInstance> items = player.getInventory().getItems();
        items.stream().filter(ItemInstance::isTemporalItem).forEach(item -> {
            final int remainingSec = item.getPeriod();
            final String remainingText = Util.formatTime(remainingSec);
            player.sendMessage(new CustomMessage("voicedcommandhandlers.ItemRemaining", player, item, remainingText));
        });
    }
}
