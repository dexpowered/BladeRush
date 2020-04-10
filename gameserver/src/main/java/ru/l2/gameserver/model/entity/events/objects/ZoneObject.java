package ru.l2.gameserver.model.entity.events.objects;

import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Zone;
import ru.l2.gameserver.model.entity.Reflection;
import ru.l2.gameserver.model.entity.events.GlobalEvent;

import java.util.List;

public class ZoneObject implements InitableObject {
    private final String _name;
    private Zone _zone;

    public ZoneObject(final String name) {
        _name = name;
    }

    @Override
    public void initObject(final GlobalEvent e) {
        final Reflection r = e.getReflection();
        _zone = r.getZone(_name);
    }

    public void setActive(final boolean a) {
        _zone.setActive(a);
    }

    public void setActive(final boolean a, final GlobalEvent event) {
        setActive(a);
    }

    public Zone getZone() {
        return _zone;
    }

    public List<Player> getInsidePlayers() {
        return _zone.getInsidePlayers();
    }

    public boolean checkIfInZone(final Creature c) {
        return _zone.checkIfInZone(c);
    }
}
