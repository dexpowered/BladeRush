package ru.l2.gameserver.handler.admincommands.impl;

import ru.l2.gameserver.cache.Msg;
import ru.l2.gameserver.geodata.GeoEngine;
import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.instances.DoorInstance;

public class AdminDoorControl implements IAdminCommandHandler {
    @Override
    public boolean useAdminCommand(final Enum<?> comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands command = (Commands) comm;
        if (!activeChar.getPlayerAccess().Door) {
            return false;
        }
        switch (command) {
            case admin_open_all_doors: {
                GameObjectsStorage.getAllDoors().forEach(DoorInstance::openMe);
                activeChar.sendAdminMessage("All doors opened.");
                break;
            }
            case admin_close_all_doors: {
                GameObjectsStorage.getAllDoors().forEach(DoorInstance::closeMe);
                activeChar.sendAdminMessage("All doors closed.");
                break;
            }
            case admin_open: {
                GameObject target;
                if (wordList.length > 1) {
                    target = World.getAroundObjectById(activeChar, Integer.parseInt(wordList[1]));
                } else {
                    target = activeChar.getTarget();
                }
                if (target != null && target.isDoor()) {
                    ((DoorInstance) target).openMe();
                    break;
                }
                activeChar.sendPacket(Msg.INVALID_TARGET);
                break;
            }
            case admin_open_geo: {
                GameObject target;
                if (wordList.length > 1) {
                    target = World.getAroundObjectById(activeChar, Integer.parseInt(wordList[1]));
                } else {
                    target = activeChar.getTarget();
                }
                if (target != null && target.isDoor()) {
                    GeoEngine.removeGeoCollision(((DoorInstance) target), target.getGeoIndex());
                    break;
                }
                activeChar.sendPacket(Msg.INVALID_TARGET);
                break;
            }
            case admin_close: {
                GameObject target;
                if (wordList.length > 1) {
                    target = World.getAroundObjectById(activeChar, Integer.parseInt(wordList[1]));
                } else {
                    target = activeChar.getTarget();
                }
                if (target != null && target.isDoor()) {
                    ((DoorInstance) target).closeMe();
                    break;
                }
                activeChar.sendPacket(Msg.INVALID_TARGET);
                break;
            }
            case admin_close_geo: {
                GameObject target;
                if (wordList.length > 1) {
                    target = World.getAroundObjectById(activeChar, Integer.parseInt(wordList[1]));
                } else {
                    target = activeChar.getTarget();
                }
                if (target != null && target.isDoor()) {
                    GeoEngine.applyGeoCollision(((DoorInstance) target), target.getGeoIndex());
                    break;
                }
                activeChar.sendPacket(Msg.INVALID_TARGET);
                break;
            }
        }
        return true;
    }

    @Override
    public Enum[] getAdminCommandEnum() {
        return Commands.values();
    }

    private enum Commands {
        admin_open_all_doors,
        admin_close_all_doors,
        admin_open,
        admin_open_geo,
        admin_close,
        admin_close_geo
    }
}
