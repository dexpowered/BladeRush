package ru.l2.gameserver.model.entity.events.actions;

import ru.l2.gameserver.model.entity.events.EventAction;
import ru.l2.gameserver.model.entity.events.GlobalEvent;

public class AnnounceAction implements EventAction {
    private final int _id;

    public AnnounceAction(final int id) {
        _id = id;
    }

    @Override
    public void call(final GlobalEvent event) {
        event.announce(_id);
    }
}
