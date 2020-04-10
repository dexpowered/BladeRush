package ru.l2.gameserver.model.entity.events.objects;

import ru.l2.gameserver.model.entity.events.GlobalEvent;

import java.io.Serializable;

public interface InitableObject extends Serializable {
    void initObject(final GlobalEvent p0);
}
