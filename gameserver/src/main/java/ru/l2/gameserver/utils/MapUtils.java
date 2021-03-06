package ru.l2.gameserver.utils;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.World;

public class MapUtils {
    public static int regionX(final GameObject o) {
        return regionX(o.getX());
    }

    public static int regionY(final GameObject o) {
        return regionY(o.getY());
    }

    public static int regionX(final int x) {
        return (x - World.MAP_MIN_X >> 15) + Config.GEO_X_FIRST;
    }

    public static int regionY(final int y) {
        return (y - World.MAP_MIN_Y >> 15) + Config.GEO_Y_FIRST;
    }
}
