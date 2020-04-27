package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.data.dao.MailDAO;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.mail.Mail;
import ru.l2.gameserver.model.mail.Mail.SenderType;

import java.util.List;

public class ExShowSentPostList extends L2GameServerPacket {
    private final List<Mail> mails;

    public ExShowSentPostList(final Player cha) {
        mails = MailDAO.getInstance().getSentMailByOwnerId(cha.getObjectId());
    }

    @Override
    protected void writeImpl() {
        writeEx(0xac);
        writeD((int) (System.currentTimeMillis() / 1000L));
        writeD(mails.size());
        mails.stream().sorted().forEach(mail -> {
            writeD(mail.getMessageId());
            writeS(mail.getTopic());
            writeS(mail.getReceiverName());
            writeD(mail.isPayOnDelivery() ? 1 : 0);
            writeD(mail.getExpireTime());
            writeD(mail.isUnread() ? 1 : 0);
            writeD((mail.getType() != SenderType.NORMAL) ? 1 : 0);
            writeD(mail.getAttachments().isEmpty() ? 0 : 1);
        });
    }
}
