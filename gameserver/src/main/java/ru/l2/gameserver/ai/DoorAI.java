package ru.l2.gameserver.ai;

import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.GameObject;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.entity.events.impl.SiegeEvent;
import ru.l2.gameserver.model.instances.DoorInstance;

public class DoorAI extends CharacterAI {
    public DoorAI(final DoorInstance actor) {
        super(actor);
    }

    public void onEvtTwiceClick(final Player player) {
    }

    public void onEvtOpen(final Player player) {
    }

    public void onEvtClose(final Player player) {
    }

    @Override
    public DoorInstance getActor() {
        return (DoorInstance) super.getActor();
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final Creature actor;
        if (attacker == null || (actor = getActor()) == null) {
            return;
        }
        final Player player = attacker.getPlayer();
        if (player == null) {
            return;
        }
        final SiegeEvent<?, ?> siegeEvent1 = player.getEvent(SiegeEvent.class);
        final SiegeEvent<?, ?> siegeEvent2 = actor.getEvent(SiegeEvent.class);
        if (siegeEvent1 == null || (siegeEvent1 == siegeEvent2 && siegeEvent1.getSiegeClan("attackers", player.getClan()) != null)) {
            actor.getAroundNpc(900, 200).stream().filter(GameObject::isSiegeGuard).forEach(npc -> {
                if (Rnd.chance(20)) {
                    npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 10000);
                } else {
                    npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 2000);
                }
            });
        }
    }
}
