package npc.model;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.DoorInstance;
import ru.l2.gameserver.model.instances.GuardInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.ReflectionUtils;

public class BorderOutpostDoormanInstance extends GuardInstance {
    public BorderOutpostDoormanInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(final Player player, final String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }
        switch (command) {
            case "openDoor": {
                final DoorInstance door = ReflectionUtils.getDoor(24170001);
                door.openMe();
                break;
            }
            case "closeDoor": {
                final DoorInstance door = ReflectionUtils.getDoor(24170001);
                door.closeMe();
                break;
            }
            default:
                super.onBypassFeedback(player, command);
                break;
        }
    }
}
