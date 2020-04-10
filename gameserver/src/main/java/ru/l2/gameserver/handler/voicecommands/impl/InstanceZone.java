package ru.l2.gameserver.handler.voicecommands.impl;

import ru.l2.gameserver.data.xml.holder.InstantZoneHolder;
import ru.l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.scripts.Functions;

public class InstanceZone extends Functions implements IVoicedCommandHandler {
    private final String[] _commandList;

    public InstanceZone() {
        _commandList = new String[]{"instancezone"};
    }

    @Override
    public boolean useVoicedCommand(final String command, final Player activeChar, final String args) {
        if (activeChar == null) {
            return false;
        }
        if (activeChar.getActiveReflection() != null) {
            activeChar.sendMessage(new CustomMessage("INSTANT_ZONE_CURRENTLY_IN_USE_S1", activeChar).addString(activeChar.getActiveReflection().getName()));
        }
        boolean noLimit = true;
        boolean showMsg = false;
        for (final int i : activeChar.getInstanceReuses().keySet()) {
            final int limit = InstantZoneHolder.getInstance().getMinutesToNextEntrance(i, activeChar);
            if (limit > 0) {
                noLimit = false;
                if (!showMsg) {
                    activeChar.sendMessage(new CustomMessage("INSTANCE_ZONE_TIME_LIMIT", activeChar));
                    showMsg = true;
                }
                activeChar.sendMessage(new CustomMessage("S1_WILL_BE_AVAILABLE_FOR_REUSE_AFTER_S2_HOURS_S3_MINUTES", activeChar).addNumber(i).addNumber(limit / 60).addNumber(limit % 60));
            }
        }
        if (noLimit) {
            activeChar.sendMessage(new CustomMessage("THERE_IS_NO_INSTANCE_ZONE_UNDER_A_TIME_LIMIT", activeChar));
        }
        return true;
    }

    @Override
    public String[] getVoicedCommandList() {
        return _commandList;
    }
}
