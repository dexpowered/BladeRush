package ai.door;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.gameserver.GameTimeController;
import ru.l2.gameserver.ai.DoorAI;
import ru.l2.gameserver.listener.game.OnDayNightChangeListener;
import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.model.instances.DoorInstance;

public class OnNightOpen extends DoorAI {
    public OnNightOpen(final DoorInstance actor) {
        super(actor);
        GameTimeController.getInstance().addListener(new NightDoorOpenController(actor));
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }

    private static class NightDoorOpenController implements OnDayNightChangeListener {
        private final HardReference<? extends DoorInstance> _actRef;

        NightDoorOpenController(final DoorInstance actor) {
            _actRef = actor.getRef();
        }

        @Override
        public void onDay() {
        }

        @Override
        public void onNight() {
            final DoorInstance door = _actRef.get();
            if (door != null && door.getReflection() == ReflectionManager.DEFAULT) {
                door.openMe();
                LOGGER.info("Zaken door (Location : " + door.getLoc() + ") is opened for 5 min.");
            } else {
                LOGGER.warn("Zaken door is null");
            }
        }
    }
}
