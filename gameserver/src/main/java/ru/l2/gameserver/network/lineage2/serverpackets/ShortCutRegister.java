package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.actor.instances.player.ShortCut;

public class ShortCutRegister extends ShortCutPacket {
    private final ShortcutInfo _shortcutInfo;

    public ShortCutRegister(final Player player, final ShortCut sc) {
        _shortcutInfo = ShortCutPacket.convert(player, sc);
    }

    @Override
    protected final void writeImpl() {
        writeC(0x44);
        _shortcutInfo.write(this);
    }
}
