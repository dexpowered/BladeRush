package ru.l2.gameserver.model.entity.events.actions;

import ru.l2.gameserver.model.entity.events.EventAction;
import ru.l2.gameserver.model.entity.events.GlobalEvent;

public class RefreshAction implements EventAction {
    private final String _name;

    public RefreshAction(final String name) {
        _name = name;
    }

    @Override
    public void call(final GlobalEvent event) {
        event.refreshAction(_name);
    }
}
