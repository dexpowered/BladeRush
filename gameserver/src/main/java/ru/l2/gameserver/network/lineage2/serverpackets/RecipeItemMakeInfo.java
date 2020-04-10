package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Recipe;

public class RecipeItemMakeInfo extends L2GameServerPacket {
    private final int _id;
    private final int _typeOrd;
    private final int _status;
    private final int _curMP;
    private final int _maxMP;

    public RecipeItemMakeInfo(final Player player, final Recipe recipe, final int status) {
        _id = recipe.getId();
        _typeOrd = recipe.getType().ordinal();
        _status = status;
        _curMP = (int) player.getCurrentMp();
        _maxMP = player.getMaxMp();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xd7);
        writeD(_id);
        writeD(_typeOrd);
        writeD(_curMP);
        writeD(_maxMP);
        writeD(_status);
    }
}
