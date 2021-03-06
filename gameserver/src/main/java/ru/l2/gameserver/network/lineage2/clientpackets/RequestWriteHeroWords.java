package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.olympiad.HeroManager;

public class RequestWriteHeroWords extends L2GameClientPacket {
    private String _heroWords;

    @Override
    protected void readImpl() {
        _heroWords = readS();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null || !player.isHero()) {
            return;
        }
        if (_heroWords == null || _heroWords.length() > 300) {
            return;
        }
        HeroManager.getInstance().setHeroMessage(player.getObjectId(), _heroWords);
    }
}
