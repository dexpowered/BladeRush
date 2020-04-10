package ru.l2.gameserver.templates.spawn;

import ru.l2.gameserver.utils.Location;

public interface SpawnRange {
    Location getRandomLoc(final int p0);
}
