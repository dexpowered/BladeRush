package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.handler.bbs.CommunityBoardManager;
import ru.l2.gameserver.handler.bbs.ICommunityBoardHandler;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.network.lineage2.serverpackets.SystemMessage;

public class RequestShowboard extends L2GameClientPacket {
    private int _unknown;

    @Override
    public void readImpl() {
        _unknown = readD();
    }

    @Override
    public void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (Config.COMMUNITYBOARD_ENABLED) {
            final ICommunityBoardHandler handler = CommunityBoardManager.getInstance().getCommunityHandler(Config.BBS_DEFAULT);
            if (handler != null) {
                handler.onBypassCommand(activeChar, Config.BBS_DEFAULT);
            }
        } else {
            activeChar.sendPacket(new SystemMessage(938));
        }
    }
}
