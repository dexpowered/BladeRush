package ru.l2.gameserver.taskmanager.actionrunner.tasks;

import ru.l2.commons.dao.JdbcEntityState;
import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.data.dao.MailDAO;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.mail.Mail;
import ru.l2.gameserver.model.mail.Mail.SenderType;
import ru.l2.gameserver.network.lineage2.serverpackets.ExNoticePostArrived;

import java.util.List;

public class DeleteExpiredMailTask extends AutomaticTask {
    @Override
    public void doTask() {
        final int expireTime = (int) (System.currentTimeMillis() / 1000L);
        final List<Mail> mails = MailDAO.getInstance().getExpiredMail(expireTime);
        for (final Mail mail : mails) {
            if (!mail.getAttachments().isEmpty()) {
                if (mail.getType() == SenderType.NORMAL) {
                    final Player player = World.getPlayer(mail.getSenderId());
                    final Mail reject = mail.reject();
                    MailDAO.getInstance().deleteReceivedMailByMailId(mail.getReceiverId(), mail.getMessageId());
                    MailDAO.getInstance().deleteSentMailByMailId(mail.getReceiverId(), mail.getMessageId());
                    mail.delete();
                    reject.setExpireTime(expireTime + 1296000);
                    reject.save();
                    if (player == null) {
                        continue;
                    }
                    player.sendPacket(ExNoticePostArrived.STATIC_TRUE);
                    player.sendPacket(Msg.THE_MAIL_HAS_ARRIVED);
                } else {
                    mail.setExpireTime(expireTime + 86400);
                    mail.setJdbcState(JdbcEntityState.UPDATED);
                    mail.update();
                }
            } else {
                MailDAO.getInstance().deleteReceivedMailByMailId(mail.getReceiverId(), mail.getMessageId());
                MailDAO.getInstance().deleteSentMailByMailId(mail.getReceiverId(), mail.getMessageId());
                mail.delete();
            }
        }
    }

    @Override
    public long reCalcTime(final boolean start) {
        return System.currentTimeMillis() + 600000L;
    }
}
