package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.data.dao.MailDAO;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.mail.Mail;
import ru.l2.gameserver.network.lineage2.serverpackets.ExReplySentPost;
import ru.l2.gameserver.network.lineage2.serverpackets.ExShowSentPostList;

public class RequestExRequestSentPost extends L2GameClientPacket {
    private int postId;

    @Override
    protected void readImpl() {
        postId = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Mail mail = MailDAO.getInstance().getSentMailByMailId(activeChar.getObjectId(), postId);
        if (mail != null) {
            activeChar.sendPacket(new ExReplySentPost(mail));
            return;
        }
        activeChar.sendPacket(new ExShowSentPostList(activeChar));
    }
}
