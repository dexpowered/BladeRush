package ru.l2.gameserver.model.entity.events.actions;

import ru.l2.gameserver.model.entity.events.EventAction;
import ru.l2.gameserver.model.entity.events.GlobalEvent;

public class TeleportPlayersAction implements EventAction {
    private final String _name;

    public TeleportPlayersAction(final String name) {
        _name = name;
    }

    @Override
    public void call(final GlobalEvent event) {
        event.teleportPlayers(_name);
    }
}
