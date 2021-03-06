package ru.custom.phantoms.model;

import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.data.dao.CharacterDAO;
import ru.l2.gameserver.model.AggroList;
import ru.l2.gameserver.model.Player;
import ru.custom.phantoms.PhantomConfig;
import ru.custom.phantoms.action.AbstractPhantomAction;
import ru.custom.phantoms.ai.AbstractPhantomAi;
import ru.custom.phantoms.ai.PhantomDelayType;
import ru.custom.phantoms.data.holder.PhantomOnlineHolder;
import ru.l2.gameserver.templates.PlayerTemplate;
import ru.l2.gameserver.utils.Location;

import java.util.concurrent.Future;

public class Phantom extends Player {
    private final PhantomMemory memory = new PhantomMemory(this);
    private Future<?> spawnTask;
    private Future<?> despawnTask;
    private Future<?> aiTask;
    private Future<?> actionTask;
    private Location spawnLoc;

    public Phantom(final int objectId, final PlayerTemplate template, final String accountName) {
        super(objectId, template, accountName);
    }

    public long getRndDelay(final long delay) {
        return Rnd.get(delay, delay + delay / 2L);
    }

    public void schedulePhantomSpawn() {
        spawnTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl() {
            @Override
            public void runImpl() {
                spawnLoc = getLoc();
                spawnMe();
                startPhantomAi();
                schedulePhantomDespawn();
            }
        }, (long) Rnd.get(PhantomConfig.phantomSpawnDelayMinMax[0] * 60000, PhantomConfig.phantomSpawnDelayMinMax[1] * 60000));
    }

    public void schedulePhantomDespawn() {
        despawnTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl() {
            @Override
            public void runImpl() {
                despawnPhantom();
            }
        }, (long) Rnd.get(PhantomConfig.phantomDespawnDelayMinMax[0] * 60000, PhantomConfig.phantomDespawnDelayMinMax[1] * 60000));
    }

    public void despawnPhantom() {
        final int objId = getObjectId();
        stopPhantomAi();
        if (despawnTask != null && !despawnTask.isCancelled()) {
            despawnTask.cancel(false);
        }
        kick();
        PhantomOnlineHolder.getInstance().deletePhantom(this);
        CharacterDAO.getInstance().deleteCharacterDataByObjId(objId, false);
    }

    @Override
    public AbstractPhantomAi getAI() {
        return (AbstractPhantomAi) _ai;
    }

    public void startPhantomAi() {
        aiTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(getAI(), getPhantomAiDelay(PhantomDelayType.AI_INIT), getPhantomAiDelay(PhantomDelayType.AI_TICK));
    }

    public boolean stopPhantomAi() {
        stopActionTask();
        return aiTask.cancel(true);
    }

    public void stopActionTask() {
        if (actionTask != null && !actionTask.isCancelled()) {
            actionTask.cancel(true);
        }
    }

    public void updateAi(final AbstractPhantomAi ai) {
        stopPhantomAi();
        setAI(ai);
        startPhantomAi();
    }

    private long getPhantomAiDelay(final PhantomDelayType delayType) {
        switch (getAI().getType()) {
            case TOWN: {
                switch (delayType) {
                    case AI_INIT: {
                        return getRndDelay(PhantomConfig.townAiInit);
                    }
                    case AI_TICK: {
                        return getRndDelay(PhantomConfig.townAiTick);
                    }
                    default: {
                        break;
                    }
                }
            }
            case PVP: {
                switch (delayType) {
                    case AI_INIT: {
                        return getRndDelay(PhantomConfig.townAiInit);
                    }
                    case AI_TICK: {
                        return getRndDelay(PhantomConfig.townAiTick);
                    }
                    default: {
                        break;
                    }
                }
            }
            case EVENTS: {
                switch (delayType) {
                    case AI_INIT: {
                        return getRndDelay(PhantomConfig.townAiInit);
                    }
                    case AI_TICK: {
                        return getRndDelay(PhantomConfig.townAiTick);
                    }
                    default: {
                        break;
                    }
                }
            }
            case OLYMPIAD: {
                switch (delayType) {
                    case AI_INIT: {
                        return getRndDelay(PhantomConfig.townAiInit);
                    }
                    case AI_TICK: {
                        return getRndDelay(PhantomConfig.townAiTick);
                    }
                    default: {
                        break;
                    }
                }
            }
            case FARM: {
                switch (delayType) {
                    case AI_INIT: {
                        return getRndDelay(PhantomConfig.townAiInit);
                    }
                    case AI_TICK: {
                        return getRndDelay(PhantomConfig.townAiTick);
                    }
                    default: {
                        break;
                    }
                }
            }
            case TRADE: {
                switch (delayType) {
                    case AI_INIT: {
                        return getRndDelay(PhantomConfig.townAiInit);
                    }
                    case AI_TICK: {
                        return getRndDelay(PhantomConfig.townAiTick);
                    }
                    default: {
                        break;
                    }
                }
            }
        }
        return 0L;
    }

    public void doAction(final AbstractPhantomAction action) {
        action.setActor(this);
        actionTask = action.schedule();
    }

    public AggroList getAggroList() {
        return getMemory().getAggroList();
    }

    @Override
    public boolean isPhantom() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    public PhantomMemory getMemory() {
        return memory;
    }

    public Location getSpawnLoc() {
        return spawnLoc;
    }
}
