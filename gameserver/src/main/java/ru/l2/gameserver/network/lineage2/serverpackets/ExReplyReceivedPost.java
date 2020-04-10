package ru.l2.gameserver.network.lineage2.serverpackets;

import ru.l2.gameserver.model.mail.Mail;
import ru.l2.gameserver.model.mail.Mail.SenderType;

public class ExReplyReceivedPost extends L2GameServerPacket {
    private final Mail mail;

    public ExReplyReceivedPost(final Mail mail) {
        this.mail = mail;
    }

    @Override
    protected void writeImpl() {
        writeEx(0xab);
        writeD(mail.getMessageId());
        writeD(mail.isPayOnDelivery() ? 1 : 0);
        writeD((mail.getType() != SenderType.NORMAL) ? 1 : 0);
        writeS(mail.getSenderName());
        writeS(mail.getTopic());
        writeS(mail.getBody());
        writeD(mail.getAttachments().size());
        mail.getAttachments().forEach(item -> {
            writeItemInfo(item);
            writeD(item.getObjectId());
        });
        writeQ(mail.getPrice());
        writeD((mail.getAttachments().size() > 0) ? 1 : 0);
        writeD(mail.getType().ordinal());
    }
}
