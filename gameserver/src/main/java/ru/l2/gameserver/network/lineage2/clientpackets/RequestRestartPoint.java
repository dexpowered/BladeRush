package ru.l2.gameserver.network.lineage2.clientpackets;

import org.apache.commons.lang3.tuple.Pair;
import ru.l2.commons.lang.ArrayUtils;
import ru.l2.gameserver.listener.actor.player.OnAnswerListener;
import ru.l2.gameserver.listener.actor.player.impl.ReviveAnswerListener;
import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.base.RestartType;
import ru.l2.gameserver.model.entity.Reflection;
import ru.l2.gameserver.model.entity.events.GlobalEvent;
import ru.l2.gameserver.model.entity.residence.Castle;
import ru.l2.gameserver.model.entity.residence.ClanHall;
import ru.l2.gameserver.model.pledge.Clan;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ActionFail;
import ru.l2.gameserver.network.lineage2.serverpackets.Die;
import ru.l2.gameserver.utils.ItemFunctions;
import ru.l2.gameserver.utils.Location;
import ru.l2.gameserver.utils.TeleportUtils;

public class RequestRestartPoint extends L2GameClientPacket {
    private RestartType _restartType;

    public static Location defaultLoc(final RestartType restartType, final Player activeChar) {
        Location loc = null;
        final Clan clan = activeChar.getClan();
        switch (restartType) {
            case TO_CLANHALL: {
                if (clan != null && clan.getHasHideout() != 0) {
                    final ClanHall clanHall = activeChar.getClanHall();
                    loc = TeleportUtils.getRestartLocation(activeChar, RestartType.TO_CLANHALL);
                    if (clanHall.getFunction(5) != null) {
                        activeChar.restoreExp(clanHall.getFunction(5).getLevel());
                    }
                    break;
                }
                break;
            }
            case TO_CASTLE: {
                if (clan != null && clan.getCastle() != 0) {
                    final Castle castle = activeChar.getCastle();
                    loc = TeleportUtils.getRestartLocation(activeChar, RestartType.TO_CASTLE);
                    if (castle.getFunction(5) != null) {
                        activeChar.restoreExp(castle.getFunction(5).getLevel());
                    }
                    break;
                }
                break;
            }
            default: {
                loc = TeleportUtils.getRestartLocation(activeChar, RestartType.TO_VILLAGE);
                break;
            }
        }
        return loc;
    }

    @Override
    protected void readImpl() {
        _restartType = ArrayUtils.valid(RestartType.VALUES, readD());
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (_restartType == null || activeChar == null) {
            return;
        }
        if (activeChar.isFakeDeath()) {
            activeChar.breakFakeDeath();
            return;
        }
        if (!activeChar.isDead() && !activeChar.isGM()) {
            activeChar.sendActionFailed();
            return;
        }
        if (activeChar.isFestivalParticipant()) {
            activeChar.doRevive();
            return;
        }
        if (activeChar.isResurectProhibited()) {
            activeChar.sendActionFailed();
            return;
        }
        switch (_restartType) {
            case FIXED: {
                if (activeChar.getPlayerAccess().ResurectFixed) {
                    activeChar.doRevive(100.0);
                    break;
                }
                if (ItemFunctions.removeItem(activeChar, 9218, 1L, true) == 1L) {
                    activeChar.sendMessage(new CustomMessage("YOU_HAVE_USED_THE_FEATHER_OF_BLESSING_TO_RESURRECT", activeChar));
                    activeChar.doRevive(100.0);
                    break;
                }
                activeChar.sendPacket(ActionFail.STATIC, new Die(activeChar));
                break;
            }
            default: {
                Location loc = null;
                final Reflection ref = activeChar.getReflection();
                if (ref == ReflectionManager.DEFAULT) {
                    for (final GlobalEvent e : activeChar.getEvents()) {
                        loc = e.getRestartLoc(activeChar, _restartType);
                    }
                }
                if (loc == null) {
                    loc = defaultLoc(_restartType, activeChar);
                }
                if (loc != null) {
                    final Pair<Integer, OnAnswerListener> ask = activeChar.getAskListener(false);
                    if (ask != null && ask.getValue() instanceof ReviveAnswerListener && !((ReviveAnswerListener) ask.getValue()).isForPet()) {
                        activeChar.getAskListener(true);
                    }
                    activeChar.setPendingRevive(true);
                    activeChar.teleToLocation(loc, ReflectionManager.DEFAULT);
                    break;
                }
                activeChar.sendPacket(ActionFail.STATIC, new Die(activeChar));
                break;
            }
        }
    }
}
