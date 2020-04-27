package services;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;
import ru.l2.gameserver.data.scripts.Functions;

public class ManaRegen extends Functions {
    private static final int ADENA = 57;
    private static final long PRICE = 5L;

    public void DoManaRegen() {
        final Player player = getSelf();
        final long mp = (long) Math.floor(player.getMaxMp() - player.getCurrentMp());
        final long fullCost = mp * 5L;
        if (fullCost <= 0L) {
            player.sendPacket(Msg.NOTHING_HAPPENED);
            return;
        }
        if (getItemCount(player, 57) >= fullCost) {
            removeItem(player, 57, fullCost);
            player.sendPacket(new SystemMessage(1068).addNumber(mp));
            player.setCurrentMp((double) player.getMaxMp());
        } else {
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        }
    }
}
