package ru.l2.gameserver.handler.admincommands.impl;

import org.apache.commons.lang3.math.NumberUtils;
import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Spawner;
import ru.l2.gameserver.model.instances.NpcInstance;

public class AdminDelete implements IAdminCommandHandler {
    @Override
    public boolean useAdminCommand(final Enum<?> comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands command = (Commands) comm;
        if (!activeChar.getPlayerAccess().CanEditNPC) {
            return false;
        }
        switch (command) {
            case admin_delete: {
                final GameObject obj = (wordList.length == 1) ? activeChar.getTarget() : GameObjectsStorage.getNpc(NumberUtils.toInt(wordList[1]));
                if (obj != null && obj.isNpc()) {
                    final NpcInstance target = (NpcInstance) obj;
                    target.deleteMe();
                    final Spawner spawn = target.getSpawn();
                    if (spawn != null) {
                        spawn.stopRespawn();
                    }
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
        admin_delete
    }
}
