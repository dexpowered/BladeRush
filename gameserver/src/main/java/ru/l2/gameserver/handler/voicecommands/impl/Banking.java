package ru.l2.gameserver.handler.voicecommands.impl;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.data.scripts.Functions;

public class Banking extends Functions implements IVoicedCommandHandler {
    private final String[] _commandList = {"deposit", "withdraw", "adena", "goldbar", "gb"};

    public Banking() {
    }

    @Override
    public boolean useVoicedCommand(String command, final Player activeChar, final String args) {
        if (!Config.SERVICES_BANKING_ENABLED) {
            return false;
        }
        command = command.intern();
        if (command.equalsIgnoreCase("deposit") || command.equalsIgnoreCase("goldbar") || command.equalsIgnoreCase("gb")) {
            return deposit(command, activeChar, args);
        }
        return (command.equalsIgnoreCase("withdraw") || command.equalsIgnoreCase("adena")) && withdraw(command, activeChar, args);
    }

    public boolean deposit(final String command, final Player activeChar, final String args) {
        if (Functions.getItemCount(activeChar, Config.SERVICES_DEPOSIT_ITEM_ID_NEEDED) < Config.SERVICES_DEPOSIT_ITEM_COUNT_NEEDED) {
            activeChar.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return false;
        }
        Functions.removeItem(activeChar, Config.SERVICES_DEPOSIT_ITEM_ID_NEEDED, Config.SERVICES_DEPOSIT_ITEM_COUNT_NEEDED);
        activeChar.sendMessage("Deposit successfully converted");
        Functions.addItem(activeChar, Config.SERVICES_DEPOSIT_ITEM_ID_GIVED, Config.SERVICES_DEPOSIT_ITEM_COUNT_GIVED);
        return true;
    }

    public boolean withdraw(final String command, final Player activeChar, final String args) {
        if (Functions.getItemCount(activeChar, Config.SERVICES_WITHDRAW_ITEM_ID_NEEDED) < Config.SERVICES_WITHDRAW_ITEM_COUNT_NEEDED) {
            activeChar.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return false;
        }
        Functions.removeItem(activeChar, Config.SERVICES_WITHDRAW_ITEM_ID_NEEDED, Config.SERVICES_WITHDRAW_ITEM_COUNT_NEEDED);
        activeChar.sendMessage("Withdraw successfully converted");
        Functions.addItem(activeChar, Config.SERVICES_WITHDRAW_ITEM_ID_GIVED, Config.SERVICES_WITHDRAW_ITEM_COUNT_GIVED);
        return true;
    }

    @Override
    public String[] getVoicedCommandList() {
        return _commandList;
    }
}
