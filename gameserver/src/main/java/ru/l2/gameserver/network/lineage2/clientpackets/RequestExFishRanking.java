package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.manager.games.FishingChampionShipManager;
import ru.l2.gameserver.model.Player;

public class RequestExFishRanking extends L2GameClientPacket {
    @Override
    protected void readImpl() {
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (Config.ALT_FISH_CHAMPIONSHIP_ENABLED) {
            FishingChampionShipManager.getInstance().showMidResult(getClient().getActiveChar());
        }
    }
}
