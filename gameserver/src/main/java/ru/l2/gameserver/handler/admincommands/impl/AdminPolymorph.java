package ru.l2.gameserver.handler.admincommands.impl;

import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.data.xml.holder.NpcTemplateHolder;
import ru.l2.gameserver.handler.admincommands.IAdminCommandHandler;
import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.Player;

public class AdminPolymorph implements IAdminCommandHandler {
    @Override
    public boolean useAdminCommand(final Enum<?> comm, final String[] wordList, final String fullString, final Player activeChar) {
        final Commands command = (Commands) comm;
        if (!activeChar.getPlayerAccess().CanPolymorph) {
            return false;
        }
        GameObject target = activeChar.getTarget();
        switch (command) {
            case admin_polyself: {
                target = activeChar;
            }
            case admin_polymorph:
            case admin_poly: {
                if (target == null || !target.isPlayer()) {
                    activeChar.sendPacket(Msg.INVALID_TARGET);
                    return false;
                }
                try {
                    final int id = Integer.parseInt(wordList[1]);
                    if (NpcTemplateHolder.getInstance().getTemplate(id) != null) {
                        ((Player) target).setPolyId(id);
                        ((Player) target).broadcastCharInfo();
                    }
                    break;
                } catch (Exception e) {
                    activeChar.sendMessage("USAGE: //poly id [type:npc|item]");
                    return false;
                }
            }
            case admin_unpolyself: {
                target = activeChar;
            }
            case admin_unpolymorph:
            case admin_unpoly: {
                if (target == null || !target.isPlayer()) {
                    activeChar.sendPacket(Msg.INVALID_TARGET);
                    return false;
                }
                ((Player) target).setPolyId(0);
                ((Player) target).broadcastCharInfo();
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
        admin_polyself,
        admin_polymorph,
        admin_poly,
        admin_unpolyself,
        admin_unpolymorph,
        admin_unpoly
    }
}
