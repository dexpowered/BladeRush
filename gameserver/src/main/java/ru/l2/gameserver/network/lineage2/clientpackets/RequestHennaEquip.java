package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.data.xml.holder.HennaHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.templates.Henna;

public class RequestHennaEquip extends L2GameClientPacket {
    private int _symbolId;

    @Override
    protected void readImpl() {
        _symbolId = readD();
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null) {
            return;
        }
        final Henna temp = HennaHolder.getInstance().getHenna(_symbolId);
        if (temp == null || !temp.isForThisClass(player)) {
            player.sendPacket(Msg.THE_SYMBOL_CANNOT_BE_DRAWN);
            return;
        }
        final long adena = player.getAdena();
        final long countDye = player.getInventory().getCountOf(temp.getDyeId());
        if (countDye >= temp.getDrawCount() && adena >= temp.getPrice()) {
            if (player.consumeItem(temp.getDyeId(), temp.getDrawCount()) && player.reduceAdena(temp.getPrice())) {
                player.sendPacket(SystemMsg.THE_SYMBOL_HAS_BEEN_ADDED);
                player.addHenna(temp);
            }
        } else {
            player.sendPacket(SystemMsg.THE_SYMBOL_CANNOT_BE_DRAWN);
        }
    }
}
