package ru.l2.gameserver.phantoms.action;

import ru.l2.gameserver.model.instances.MonsterInstance;

/**
 * Created by JunkyFunky
 * on 03.07.2018 11:38
 * group j2dev
 */
public class AttackMonsterAction extends AbstractPhantomAction {
    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public void run() {
        final MonsterInstance monsterInstance = (MonsterInstance) actor.getTarget();
        actor.doAttack(monsterInstance);
        //todo skills attack
    }
}
