package ru.l2.gameserver.model.entity.events.objects;

import ru.l2.gameserver.model.entity.events.GlobalEvent;

import java.io.Serializable;

public interface SpawnableObject extends Serializable {
    void spawnObject(final GlobalEvent p0);

    void despawnObject(final GlobalEvent p0);

    void refreshObject(final GlobalEvent p0);
}
