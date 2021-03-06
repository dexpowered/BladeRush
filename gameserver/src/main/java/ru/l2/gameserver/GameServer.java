package ru.l2.gameserver;

import com.stringer.annotations.HideAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.custom.geoengine.GeoEngine;
import ru.custom.phantoms.PhantomLoader;
import ru.l2.commons.dbutils.SqlTableOptimizer;
import ru.l2.commons.lang.StatsUtils;
import ru.l2.commons.listener.ListenerList;
import ru.l2.commons.net.nio.impl.SelectorThread;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.versioning.Version;
import ru.l2.gameserver.data.cache.CrestCache;
import ru.l2.gameserver.data.dao.CharacterDAO;
import ru.l2.gameserver.data.dao.ItemsDAO;
import ru.l2.gameserver.data.scripts.Scripts;
import ru.l2.gameserver.data.xml.Parsers;
import ru.l2.gameserver.data.xml.holder.BoatHolder;
import ru.l2.gameserver.data.xml.holder.EventHolder;
import ru.l2.gameserver.data.xml.holder.ResidenceHolder;
import ru.l2.gameserver.data.xml.holder.StaticObjectHolder;
import ru.l2.gameserver.database.DatabaseFactory;
import ru.l2.gameserver.handler.admincommands.AdminCommandHandler;
import ru.l2.gameserver.handler.bypass.BypassHolder;
import ru.l2.gameserver.handler.items.ItemHandler;
import ru.l2.gameserver.handler.npcdialog.NpcDialogAppenderHolder;
import ru.l2.gameserver.handler.onshiftaction.OnShiftActionHolder;
import ru.l2.gameserver.handler.usercommands.UserCommandHandler;
import ru.l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import ru.l2.gameserver.idfactory.IdFactory;
import ru.l2.gameserver.listener.GameListener;
import ru.l2.gameserver.listener.game.OnShutdownListener;
import ru.l2.gameserver.listener.game.OnStartListener;
import ru.l2.gameserver.manager.*;
import ru.l2.gameserver.manager.games.CustomTradeManagers;
import ru.l2.gameserver.manager.games.FishingChampionShipManager;
import ru.l2.gameserver.manager.games.LotteryManager;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.model.entity.MonsterRace;
import ru.l2.gameserver.model.entity.SevenSigns;
import ru.l2.gameserver.model.entity.SevenSignsFestival.SevenSignsFestival;
import ru.l2.gameserver.model.entity.olympiad.HeroManager;
import ru.l2.gameserver.model.entity.olympiad.NoblessManager;
import ru.l2.gameserver.model.entity.olympiad.OlympiadSystemManager;
import ru.l2.gameserver.network.authcomm.AuthServerCommunication;
import ru.l2.gameserver.network.lineage2.CGMHelper;
import ru.l2.gameserver.network.lineage2.GameClient;
import ru.l2.gameserver.network.lineage2.GamePacketHandler;
import ru.l2.gameserver.network.telnet.TelnetServer;
import ru.l2.gameserver.tables.*;
import ru.l2.gameserver.taskmanager.ItemsAutoDestroy;
import ru.l2.gameserver.taskmanager.L2TopRuManager;
import ru.l2.gameserver.taskmanager.TaskManager;
import ru.l2.gameserver.taskmanager.tasks.RestoreOfflineTraders;
import ru.l2.gameserver.utils.Strings;

import java.awt.*;
import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;

public class GameServer {
    public static final int AUTH_SERVER_PROTOCOL = 2;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameServer.class);
    private static final Path SQL_DIR = Paths.get("../tools/sql/game");
    public static GameServer _instance;
    private static ScheduledFuture<?> selectorTask;
    private final SelectorThread<GameClient>[] _selectorThreads;
    private final GameServerListenerList _listeners;
    private final Version version;
    private final long _serverStartTimeMillis;
    private TelnetServer statusServer;

    @SuppressWarnings("unchecked")
    public GameServer() throws Exception {
        final long serverLoadStart = System.currentTimeMillis();
        copyLog("stdout");
        copyLog("java");
        copyLog("game");
        _instance = this;
        _serverStartTimeMillis = System.currentTimeMillis();
        _listeners = new GameServerListenerList();
        new File("./log/").mkdir();
        version = new Version(GameServer.class);
        LOGGER.info("|=====================COPYRIGHT=========================|");
        LOGGER.info("Team: .................... {}", version.getTeamName());
        LOGGER.info("Site: .................... {}", version.getTeamSite());
        LOGGER.info("Jar Signature: ............ {}", version.getJarSignature());
        LOGGER.info("|======================VERSION==========================|");
        LOGGER.info("Revision: ................ {}", version.getRevisionNumber());
        LOGGER.info("Version: ................. {}", version.getVersionNumber());
        LOGGER.info("Build date: .............. {}", version.getBuildDate());
        LOGGER.info("Compiler version: ........ {}", version.getBuildJdk());
        //Version.printAllInfos();
        LOGGER.info("|==================SYSTEM INFORMATION===================|");
        StatsUtils.printOSInfo();
        StatsUtils.printCpuInfo();
        StatsUtils.printPid();
        LOGGER.info("| {}", Version.getJavaInfo());
        LOGGER.info("|=======================================================|");
        LOGGER.info("Initialize config: ........");
        Config.load();
        checkFreePorts();
        LOGGER.info("Initialize database: ........");
        DatabaseFactory.getInstance().initPool("GameServer");
        LOGGER.info("Checking database: ........");
        //SqlInstaller.checkDatabase(DatabaseFactory.getInstance().getConnection(), SQL_DIR);
        SqlTableOptimizer.repairTables(DatabaseFactory.getInstance().getConnection());
        SqlTableOptimizer.optimizeTables(DatabaseFactory.getInstance().getConnection());
        final IdFactory _idFactory = IdFactory.getInstance();
        if (!_idFactory.isInitialized()) {
            LOGGER.error("Could not read object IDs from DB. Please Check Your Data.");
            throw new Exception("Could not initialize the ID factory");
        }
        ThreadPoolManager.getInstance();
        Scripts.getInstance();
        GeoEngine.load();
        Strings.reload();
        GameTimeController.getInstance();
        World.init();
        Parsers.parseAll();
        ItemsDAO.getInstance();
        CrestCache.getInstance();
        CharacterDAO.getInstance();
        ClanTable.getInstance();
        SkillTreeTable.getInstance();
        CharTemplateTable.getInstance();
        LevelUpTable.getInstance();
        PetSkillsTable.getInstance();
        if(Config.NPC_SERVER_DELAY > 0)
        {
            ThreadPoolManager.getInstance().schedule(new NpcTaskSpawn(), Config.NPC_SERVER_DELAY * 1000);
        }
        SpawnManager.getInstance().spawnAll();
        BoatHolder.getInstance().spawnAll();
        StaticObjectHolder.getInstance().spawnAll();
        RaidBossSpawnManager.getInstance();
        Scripts.getInstance().init();
        DimensionalRiftManager.getInstance();
        Announcements.getInstance();
        LotteryManager.getInstance();
        PlayerMessageStack.getInstance();
        if (Config.AUTODESTROY_ITEM_AFTER > 0) {
            ItemsAutoDestroy.getInstance();
        }
        MonsterRace.getInstance();
        SevenSigns.getInstance();
        SevenSignsFestival.getInstance();
        SevenSigns.getInstance().updateFestivalScore();
        NoblessManager.getInstance();
        if (Config.OLY_ENABLED) {
            OlympiadSystemManager.getInstance();
            HeroManager.getInstance();
        }
        PetitionManager.getInstance();
        if (!Config.ALLOW_WEDDING) {
            CoupleManager.getInstance();
            LOGGER.info("CoupleManager initialized");
        }
        LOGGER.info("=[Handlers]=======================================");
        ItemHandler.getInstance().log();
        AdminCommandHandler.getInstance().log();
        UserCommandHandler.getInstance().log();
        VoicedCommandHandler.getInstance().log();
        BypassHolder.getInstance().log();
        NpcDialogAppenderHolder.getInstance().log();
        OnShiftActionHolder.getInstance().log();
        LOGGER.info("==================================================");
        TaskManager.getInstance();
        ClanTable.getInstance().checkClans();
        LOGGER.info("=[Events]=========================================");
        ResidenceHolder.getInstance().callInit();
        EventHolder.getInstance().callInit();
        LOGGER.info("==================================================");
        CastleManorManager.getInstance();
        Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());
        LOGGER.info("IdFactory: Free ObjectID's remaining: " + IdFactory.getInstance().size());
        CoupleManager.getInstance();
        CursedWeaponsManager.getInstance();
        if (Config.ALT_FISH_CHAMPIONSHIP_ENABLED) {
            FishingChampionShipManager.getInstance();
        }
        PhantomLoader.getInstance().loadPhantomSystem();
        L2TopRuManager.getInstance();
        Shutdown.getInstance().schedule(Config.RESTART_AT_TIME, 2);
        LOGGER.info("GameServer Started");
        LOGGER.info("Maximum Numbers of Connected Players: " + Config.MAXIMUM_ONLINE_USERS);
        final GamePacketHandler gph = new GamePacketHandler();

        InetAddress serverAddr = "*".equalsIgnoreCase(Config.GAMESERVER_HOSTNAME) ? null : InetAddress.getByName(Config.GAMESERVER_HOSTNAME);

        _selectorThreads = new SelectorThread[Config.PORTS_GAME.length];
        for (int i = 0; i < Config.PORTS_GAME.length; i++) {
            _selectorThreads[i] = new SelectorThread<>(Config.SELECTOR_CONFIG, gph, gph, gph, null);
            _selectorThreads[i].openServerSocket(serverAddr, Config.PORTS_GAME[i]);
            _selectorThreads[i].start();
        }
        AuthServerCommunication.getInstance().start();
        if (Config.SERVICES_OFFLINE_TRADE_RESTORE_AFTER_RESTART) {
            ThreadPoolManager.getInstance().schedule(new RestoreOfflineTraders(), 30000L);
        }
        getListeners().onStart();
        if (Config.IS_TELNET_ENABLED) {
            statusServer = new TelnetServer();
        } else {
            LOGGER.info("Telnet server is currently disabled.");
        }
        CGMHelper.getInstance();
        LOGGER.info("=================================================");
        System.gc();
        System.runFinalization();
        StatsUtils.printMemoryInfo();
        LOGGER.info("=================================================");
        final long serverLoadEnd = System.currentTimeMillis();
        LOGGER.info("Server Loaded in {} seconds", (serverLoadEnd - serverLoadStart) / 1000);
        if (Config.CUSTOM_SHOP_TRADER_MANAGER) {
            CustomTradeManagers.getInstance();
        }
        //TwitchRewardManager.getInstance();
        Toolkit.getDefaultToolkit().beep();
    }

    public static GameServer getInstance() {
        return _instance;
    }

    private static void checkFreePorts() {
        boolean binded = false;
        while (!binded) {
            for (final int PORT_GAME : Config.PORTS_GAME) {
                try {
                    ServerSocket ss;
                    if ("*".equalsIgnoreCase(Config.GAMESERVER_HOSTNAME)) {
                        ss = new ServerSocket(PORT_GAME);
                    } else {
                        ss = new ServerSocket(PORT_GAME, 50, InetAddress.getByName(Config.GAMESERVER_HOSTNAME));
                    }
                    ss.close();
                    binded = true;
                } catch (Exception e) {
                    LOGGER.warn("Port " + PORT_GAME + " is allready binded. Please free it and restart server.");
                    binded = false;
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @HideAccess
    public static void cancelCheckSelector() {
        if (selectorTask != null) {
            selectorTask.cancel(true);
            selectorTask = null;
        }
    }

    public static void main(final String[] args) throws Exception {
        new GameServer();
    }

    @HideAccess
    private void copyLog(final String name) {
        final File copyLog = new File("log/" + name + ".log");
        if (copyLog.exists()) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(copyLog.lastModified());
            final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy_HH-mm-ss");
            copyLog.renameTo(new File(String.format("log/%s/%s-%s.log", name, name, df.format(calendar.getTime()))));
        }
    }

    public SelectorThread<GameClient>[] getSelectorThreads() {
        return _selectorThreads;
    }

    public long getServerStartTime() {
        return _serverStartTimeMillis;
    }

    public GameServerListenerList getListeners() {
        return _listeners;
    }

    public <T extends GameListener> boolean addListener(final T listener) {
        return _listeners.add(listener);
    }

    public <T extends GameListener> boolean removeListener(final T listener) {
        return _listeners.remove(listener);
    }

    public Version getVersion() {
        return version;
    }

    public TelnetServer getStatusServer() {
        return statusServer;
    }

    public class GameServerListenerList extends ListenerList<GameServer> {
        public void onStart() {
            getListeners().stream().filter(OnStartListener.class::isInstance).forEach(listener -> ((OnStartListener) listener).onStart());
        }

        public void onShutdown() {
            getListeners().stream().filter(OnShutdownListener.class::isInstance).forEach(listener -> ((OnShutdownListener) listener).onShutdown());
        }
    }

    public class NpcTaskSpawn extends RunnableImpl {
        @Override
        public void runImpl() {
            SpawnManager.getInstance().spawnAll();
        }
    }
}
