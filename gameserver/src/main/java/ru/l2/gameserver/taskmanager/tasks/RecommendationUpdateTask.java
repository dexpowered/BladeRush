package ru.l2.gameserver.taskmanager.tasks;

import ru.l2.gameserver.Config;
import ru.l2.gameserver.model.GameObjectsStorage;
import ru.l2.gameserver.taskmanager.Task;
import ru.l2.gameserver.taskmanager.TaskManager;
import ru.l2.gameserver.taskmanager.TaskManager.ExecutedTask;
import ru.l2.gameserver.taskmanager.TaskTypes;

public class RecommendationUpdateTask extends Task {
    private static final String NAME = "sp_recommendations";

    @Override
    public void init() {
        TaskManager.addUniqueTask(NAME, TaskTypes.TYPE_GLOBAL_TASK, "1", String.format("%02d:%02d:00", Config.REC_FLUSH_HOUR, Config.REC_FLUSH_MINUTE), "");
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onTimeElapsed(final ExecutedTask task) {
        GameObjectsStorage.getPlayers().forEach(player -> {
            player.updateRecommends();
            player.broadcastUserInfo(true);
        });
    }
}
