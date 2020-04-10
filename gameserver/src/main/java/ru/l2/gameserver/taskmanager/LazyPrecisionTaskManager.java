package ru.l2.gameserver.taskmanager;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.threading.SteppingRunnableQueueManager;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.dao.AccountBonusDAO;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.network.lineage2.components.CustomMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ExShowScreenMessage;
import ru.l2.gameserver.network.lineage2.serverpackets.ExShowScreenMessage.ScreenMessageAlign;

import java.util.concurrent.Future;

public class LazyPrecisionTaskManager extends SteppingRunnableQueueManager {
    private static final LazyPrecisionTaskManager _instance = new LazyPrecisionTaskManager();

    private LazyPrecisionTaskManager() {
        super(1000L);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(this, 1000L, 1000L);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                purge();
            }
        }, 60000L, 60000L);
    }

    public static LazyPrecisionTaskManager getInstance() {
        return _instance;
    }

    public Future<?> addPCCafePointsTask(final Player player) {
        final long delay = Config.ALT_PCBANG_POINTS_DELAY * 60000L;
        return scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                if (player.isInOfflineMode() || player.getLevel() < Config.ALT_PCBANG_POINTS_MIN_LVL) {
                    return;
                }
                player.addPcBangPoints(Config.ALT_PCBANG_POINTS_BONUS, Config.ALT_PCBANG_POINTS_BONUS_DOUBLE_CHANCE > 0.0 && Rnd.chance(Config.ALT_PCBANG_POINTS_BONUS_DOUBLE_CHANCE));
            }
        }, delay, delay);
    }

    public Future<?> startBonusExpirationTask(final Player player) {
        final HardReference<Player> playerRef = player.getRef();
        final long delay = player.getBonus().getBonusExpire() * 1000L - System.currentTimeMillis();
        return schedule(new RunnableImpl() {
            @Override
            public void runImpl() {
                final Player player = playerRef.get();
                if (player == null) {
                    return;
                }
                player.getBonus().reset();
                if (player.getParty() != null) {
                    player.getParty().recalculatePartyData();
                }
                final String msg = new CustomMessage("scripts.services.RateBonus.LuckEnded", player, new Object[0]).toString();
                player.sendPacket(new ExShowScreenMessage(msg, 10000, ScreenMessageAlign.TOP_CENTER, true));
                player.sendMessage(msg);
                AccountBonusDAO.getInstance().delete(player.getAccountName());
            }
        }, delay);
    }

    public Future<?> addNpcAnimationTask(final NpcInstance npc) {
        return scheduleAtFixedRate(new RunnableImpl() {
            @Override
            public void runImpl() {
                if (npc.isVisible() && !npc.isActionsDisabled() && !npc.isMoving() && !npc.isInCombat()) {
                    npc.onRandomAnimation();
                }
            }
        }, 1000L, Rnd.get(Config.MIN_NPC_ANIMATION, Config.MAX_NPC_ANIMATION) * 1000L);
    }
}
