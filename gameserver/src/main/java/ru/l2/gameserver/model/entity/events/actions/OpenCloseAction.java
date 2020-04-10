package ru.l2.gameserver.model.entity.events.actions;

import ru.l2.gameserver.model.entity.events.EventAction;
import ru.l2.gameserver.model.entity.events.GlobalEvent;

public class OpenCloseAction implements EventAction {
    private final boolean _open;
    private final String _name;

    public OpenCloseAction(final boolean open, final String name) {
        _open = open;
        _name = name;
    }

    @Override
    public void call(final GlobalEvent event) {
        event.doorAction(_name, _open);
    }
}
