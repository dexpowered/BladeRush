package ru.custom.phantoms.action;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.model.instances.MonsterInstance;
import ru.custom.phantoms.PhantomConfig;

import java.util.List;

/**
 * Created by JunkyFunky
 * on 03.07.2018 11:34
 * group j2dev
 */
public class MoveToMonsterAction extends AbstractPhantomAction {

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public void run() {
        final List<MonsterInstance> arountMonsters = actor.getArountMonsters(PhantomConfig.moveToFarmMonsterRange, 200);
        if (arountMonsters.size() == 0) {
            return;
        }
        final MonsterInstance monsterInstance = Rnd.get(arountMonsters);
        actor.moveToLocation(monsterInstance.getLoc(), 100, true);
        actor.setTarget(monsterInstance);
    }
}
