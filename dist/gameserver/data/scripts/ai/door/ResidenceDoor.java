package ai.door;

import ru.l2.gameserver.ai.DoorAI;
import ru.l2.gameserver.data.xml.holder.ResidenceHolder;
import ru.l2.gameserver.listener.actor.player.OnAnswerListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.residence.Residence;
import ru.l2.gameserver.model.instances.DoorInstance;
import ru.l2.gameserver.network.lineage2.components.SystemMsg;
import ru.l2.gameserver.network.lineage2.serverpackets.ConfirmDlg;

public class ResidenceDoor extends DoorAI {
    public ResidenceDoor(final DoorInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtTwiceClick(final Player player) {
        final DoorInstance door = getActor();
        final Residence residence = ResidenceHolder.getInstance().getResidence(door.getTemplate().getAIParams().getInteger("residence_id"));
        if (residence.getOwner() != null && player.getClan() != null && player.getClan() == residence.getOwner() && (player.getClanPrivileges() & 0x8000) == 0x8000) {
            final SystemMsg msg = door.isOpen() ? SystemMsg.WOULD_YOU_LIKE_TO_CLOSE_THE_GATE : SystemMsg.WOULD_YOU_LIKE_TO_OPEN_THE_GATE;
            player.ask(new ConfirmDlg(msg, 0), new OnAnswerListener() {
                @Override
                public void sayYes() {
                    if (door.isOpen()) {
                        door.closeMe(player, true);
                    } else {
                        door.openMe(player, true);
                    }
                }

                @Override
                public void sayNo() {
                }
            });
        }
    }
}
