package ru.l2.gameserver.templates.arenas;

import ru.l2.gameserver.data.xml.holder.DoorHolder;
import ru.l2.gameserver.data.xml.holder.ZoneHolder;
import ru.l2.gameserver.model.instances.DoorInstance;
import ru.l2.gameserver.templates.DoorTemplate;
import ru.l2.gameserver.templates.ZoneTemplate;
import ru.l2.gameserver.utils.Location;
import ru.l2.gameserver.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunkyFunky
 * on 28.10.2017.
 * group j2dev
 */
public class CtFArena {
    private final int _id;
    private final Map<Integer, List<Location>> _teleportLocations = new HashMap<>();
    private final Map<Integer, DoorTemplate> _doorsMap = new HashMap<>();
    private Location _blueBaseLocations = new Location();
    private Location _redBaseLocations = new Location();
    private Map<String, ZoneTemplate> _zone = new HashMap<>();

    public CtFArena(final int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public void addTeleportLocation(final int team, final Location loc) {
        List<Location> locations = _teleportLocations.computeIfAbsent(team, k -> new ArrayList<>());
        locations.add(loc);
    }

    public List<Location> getTeleportLocations(final int team) {
        return _teleportLocations.get(team);
    }

    public void setBlueBaseLocation(final Location loc) {
        _blueBaseLocations = loc;
    }

    public Location getBlueBaseLocations() {
        return _blueBaseLocations;
    }

    public void setRedBaseLocation(final Location loc) {
        _redBaseLocations = loc;
    }

    public Location getRedBaseLocations() {
        return _redBaseLocations;
    }

    public Map<String, ZoneTemplate> getZones() {
        return _zone;
    }

    public void addZone(final String zone) {
        _zone.put(zone, ZoneHolder.getInstance().getTemplate(zone));
    }

    public void initDoors() {
        final List<Integer> doorsId = new ArrayList<>();
        _zone.keySet().forEach(zone -> ReflectionUtils.getZone(zone).getInsideDoors().stream()
                .mapToInt(DoorInstance::getDoorId).forEach(doorsId::add));
        doorsId.forEach(doorId -> _doorsMap.put(doorId, DoorHolder.getInstance().getTemplate(doorId)));
    }

    public Map<Integer, DoorTemplate> getArenaDoors() {
        return _doorsMap;
    }
}
