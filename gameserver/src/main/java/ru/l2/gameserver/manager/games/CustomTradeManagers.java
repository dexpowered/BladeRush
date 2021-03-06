package ru.l2.gameserver.manager.games;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.Announcements;
import ru.l2.gameserver.GameTimeController;
import ru.l2.gameserver.data.xml.holder.ShadowTradeHolder;
import ru.l2.gameserver.listener.game.OnDayNightChangeListener;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.templates.shadowtrade.ShadowTradeLoc;
import ru.l2.gameserver.utils.Log;
import ru.l2.gameserver.utils.NpcUtils;

/**
 * Created by JunkyFunky
 * on 18.01.2018 23:08
 * group j2dev
 */
public class CustomTradeManagers {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTradeManagers.class);
    private static CustomTradeManagers _instance;
    private static NpcInstance shadowTrader;
    private static NpcInstance dayTrader;

    private CustomTradeManagers() {
        LOGGER.info("CustomTrade Spawn Manager : Loading...");
        GameTimeController.getInstance().addListener(new DayNightChange());
        if (GameTimeController.getInstance().isNowNight()) {
            spawnNightManager();
        }
        if (!GameTimeController.getInstance().isNowNight() && Rnd.chance(50)) {
            spawnDayManager();
        }
    }

    public static CustomTradeManagers getInstance() {
        if (_instance == null) {
            _instance = new CustomTradeManagers();
        }
        return _instance;
    }

    private void spawnNightManager() {
        ShadowTradeLoc tempLoc = ShadowTradeHolder.getInstance().getRndNightLoc();
        int npcId = 45001;
        shadowTrader = NpcUtils.spawnSingle(npcId, tempLoc);
        Announcements.getInstance().announceByCustomMessage("ru.l2.gameserver.instancemanager.ShadowTraderManager", null);
        if (tempLoc.getDecription() != null && !tempLoc.getDecription().isEmpty()) {
            Announcements.getInstance().announceToAll(tempLoc.getDecription());
        }
        Log.add("onNight Manager Spawned in Location :" + tempLoc.toString(), "ShadowTrader");
    }

    private void spawnDayManager() {
        ShadowTradeLoc tempLoc = ShadowTradeHolder.getInstance().getRndDayLoc();
        int npcId = 45002;
        dayTrader = NpcUtils.spawnSingle(npcId, tempLoc);
        Announcements.getInstance().announceByCustomMessage("ru.l2.gameserver.instancemanager.DayTraderManger", null);
        if (tempLoc.getDecription() != null && !tempLoc.getDecription().isEmpty()) {
            Announcements.getInstance().announceToAll(tempLoc.getDecription());
        }
        Log.add("onDay Manager Spawned in Location :" + tempLoc.toString(), "DayTrader");
    }

    private class DayNightChange implements OnDayNightChangeListener {

        @Override
        public void onDay() {
            if (shadowTrader != null) {
                shadowTrader.deleteMe();
                shadowTrader = null;
            }
            if (Rnd.chance(50)) {
                spawnDayManager();
            }
        }

        @Override
        public void onNight() {
            if (dayTrader != null) {
                dayTrader.deleteMe();
                dayTrader = null;
            }
            spawnNightManager();
        }
    }

}
