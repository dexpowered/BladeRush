package ai;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.lang.reference.HardReferences;
import ru.l2.commons.util.Rnd;
import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.ai.Fighter;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.data.scripts.Functions;

public class WatchmanMonster extends Fighter {
    static final String[] flood = {"I'll be back", "You are stronger than expected"};
    static final String[] flood2 = {"Help me!", "Alarm! We are under attack!"};

    private long _lastSearch;
    private boolean isSearching;
    private HardReference<? extends Creature> _attackerRef;

    public WatchmanMonster(final NpcInstance actor) {
        super(actor);
        _lastSearch = 0L;
        isSearching = false;
        _attackerRef = HardReferences.emptyRef();
    }

    @Override
    protected void onEvtAttacked(final Creature attacker, final int damage) {
        final NpcInstance actor = getActor();
        if (attacker != null && !actor.getFaction().isNone() && actor.getCurrentHpPercents() < 50.0 && _lastSearch < System.currentTimeMillis() - 15000L) {
            _lastSearch = System.currentTimeMillis();
            _attackerRef = attacker.getRef();
            if (findHelp()) {
                return;
            }
            Functions.npcSay(actor, "Anyone, help me!");
        }
        super.onEvtAttacked(attacker, damage);
    }

    private boolean findHelp() {
        isSearching = false;
        final NpcInstance actor = getActor();
        final Creature attacker = _attackerRef.get();
        if (attacker == null) {
            return false;
        }
        for (final NpcInstance npc : actor.getAroundNpc(1000, 150)) {
            if (!actor.isDead() && npc.isInFaction(actor) && !npc.isInCombat()) {
                clearTasks();
                isSearching = true;
                addTaskMove(npc.getLoc(), true);
                Functions.npcSay(actor, WatchmanMonster.flood[Rnd.get(WatchmanMonster.flood.length)]);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onEvtDead(final Creature killer) {
        _lastSearch = 0L;
        _attackerRef = HardReferences.emptyRef();
        isSearching = false;
        super.onEvtDead(killer);
    }

    @Override
    protected void onEvtArrived() {
        final NpcInstance actor = getActor();
        if (isSearching) {
            final Creature attacker = _attackerRef.get();
            if (attacker != null) {
                Functions.npcSay(actor, WatchmanMonster.flood2[Rnd.get(WatchmanMonster.flood2.length)]);
                notifyFriends(attacker, 100);
            }
            isSearching = false;
            notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 100);
        } else {
            super.onEvtArrived();
        }
    }

    @Override
    protected void onEvtAggression(final Creature target, final int aggro) {
        if (!isSearching) {
            super.onEvtAggression(target, aggro);
        }
    }
}
