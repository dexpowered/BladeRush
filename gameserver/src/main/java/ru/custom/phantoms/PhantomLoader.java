package ru.custom.phantoms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.chat.chatfilter.ChatFilterMatcher;
import ru.l2.gameserver.network.lineage2.components.ChatType;
import ru.custom.phantoms.action.ChatAnswerAction;
import ru.custom.phantoms.data.holder.PhantomHolder;
import ru.custom.phantoms.data.holder.PhantomOnlineHolder;
import ru.custom.phantoms.data.holder.PhantomSpawnHolder;
import ru.custom.phantoms.data.parser.*;
import ru.custom.phantoms.model.Phantom;
import ru.custom.phantoms.template.PhantomSpawnTemplate;
import ru.custom.phantoms.template.PhantomTemplate;
import ru.l2.gameserver.templates.item.ItemGrade;
import ru.l2.gameserver.utils.Location;

import java.util.concurrent.Future;

public class PhantomLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhantomLoader.class);
    private static final PhantomLoader instance = new PhantomLoader();

    private Future<?> spawnWaveTask;

    public static PhantomLoader getInstance() {
        return instance;
    }

    public void loadPhantomSystem() {
        PhantomConfig.load();
        if (PhantomConfig.allowPhantoms) {
            LOGGER.info("Phantom System: loading....");
            PhantomArmorParser.getInstance().load();
            PhantomEquipParser.getInstance().load();
            PhantomParser.getInstance().load();
            PhantomSpawnParser.getInstance().load();
            PhantomPhraseParser.getInstance().load();
            getInstance().load();
        }
    }

    private void spawnPhantom(final Location location, final ItemGrade minItemGrade, final ItemGrade maxItemGrade) {
        final PhantomTemplate template = getTemplateForSpawn(minItemGrade, maxItemGrade);
        if (template == null) {
            return;
        }
        final Phantom phantom = PhantomFactory.getInstance().create(template);
        PhantomOnlineHolder.getInstance().addPhantom(phantom);
        phantom.setLoc(location);
        phantom.setHeading(Rnd.get(65535));
        phantom.schedulePhantomSpawn();
    }

    public PhantomTemplate getTemplateForSpawn(final ItemGrade minItemGrade, final ItemGrade maxItemGrade) {
        for (int i = 0; i < 20; ++i) {
            final PhantomTemplate template = PhantomHolder.getInstance().getRandomPhantomTemplate(minItemGrade, maxItemGrade);
            if (template != null && !PhantomOnlineHolder.getInstance().contains(template.getName())) {
                return template;
            }
        }
        LOGGER.warn("Can't find free PhantomTemplate! Please add more phantom templates in xml storage. [min grade: " + minItemGrade + "] [max grade: " + maxItemGrade + "].");
        return null;
    }

    private void spawnWave() {
        for (final PhantomSpawnTemplate template : PhantomSpawnHolder.getInstance().getSpawns()) {
            for (int i = 0; i < template.getCount(); ++i) {
                spawnPhantom(template.getTerritory().getRandomLoc(ReflectionManager.DEFAULT.getGeoIndex()), template.getItemGradeMin(), template.getItemGradeMax());
            }
        }
    }

    public Future<?> getSpawnWaveTask() {
        return spawnWaveTask;
    }

    public void load() {
        spawnWaveTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new SpawnWaveTask(), (long) (PhantomConfig.firstWaveDelay * 60000), (long) (PhantomConfig.waveRespawn * 60000));
        //ChatFilters.getInstance().add(new ChatFilter(new SayToPhantom(), 0, ""));
        LOGGER.info("Phantom System: completely loaded.");
    }

    private static class SayToPhantom implements ChatFilterMatcher {
        @Override
        public boolean isMatch(final Player player, final ChatType type, final String msg, final Player recipient) {
            if (recipient.isPhantom()) {
                switch (type) {
                    case TELL: {
                        ((Phantom) recipient).doAction(new ChatAnswerAction(player));
                        break;
                    }
                }
            }
            return false;
        }
    }

    private class SpawnWaveTask extends RunnableImpl {
        @Override
        public void runImpl() {
            try {
                spawnWave();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
