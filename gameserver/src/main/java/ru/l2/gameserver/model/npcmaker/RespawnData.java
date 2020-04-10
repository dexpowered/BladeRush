package ru.l2.gameserver.model.npcmaker;

import ru.l2.gameserver.utils.Location;

public class RespawnData {
    public final String dbname;
    public final long respawnTime;
    public final int currentHp;
    public final int currentMp;
    public final Location position;

    public RespawnData(String name, long respawn, int hp, int mp, int x, int y, int z) {
        dbname = name;
        respawnTime = respawn;
        currentHp = hp;
        currentMp = mp;
        position = new Location(x, y, z);
    }
}
