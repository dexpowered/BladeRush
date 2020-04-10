package npc.model;

import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.DoorInstance;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.l2.gameserver.templates.npc.NpcTemplate;

public class EventReflectionMobInstance extends MonsterInstance {
    public EventReflectionMobInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    protected void onDeath(final Creature killer) {
        super.onDeath(killer);
        if (getReflection() == killer.getReflection() && getReflection() != ReflectionManager.DEFAULT) {
            switch (getNpcId()) {
                case 25657: {
                    final DoorInstance door = getReflection().getDoor(25150002);
                    if (door != null) {
                        door.openMe();
                    }
                    break;
                }
                case 25658: {
                    final DoorInstance door = getReflection().getDoor(25150003);
                    if (door != null) {
                        door.openMe();
                        break;
                    }
                    break;
                }
            }
        }
    }
}
