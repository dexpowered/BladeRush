package ru.l2.gameserver.model.instances;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.lang.reference.HardReferences;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.model.World;
import ru.l2.gameserver.network.lineage2.serverpackets.NpcInfo;
import ru.l2.gameserver.templates.npc.NpcTemplate;

import java.util.concurrent.Future;

public class AgathionInstance extends NpcInstance {
    private static final int MAX_DISTANCE_FOR_BUFF = 300;
    private static final int BUFF_INTERVAL = 30000;
    private HardReference<Player> _playerRef;
    private Future<?> _durationCheckTask;
    private Future<?> _buffTask;

    public AgathionInstance(int objectId, final NpcTemplate template) {
        super(objectId, template);
        _playerRef = HardReferences.emptyRef();
        _durationCheckTask = null;
        _buffTask = null;
        _hasRandomWalk = false;
        _hasChatWindow = false;
        _hasRandomAnimation = true;
        setRunning();
    }

    public void buffOwner() {
        if (!isInRange(getPlayer(), MAX_DISTANCE_FOR_BUFF)) {
            getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, getPlayer());
            return;
        }
        int delay = 0;
        for (final Skill skill : getTemplate().getBuffSkills()) {
            if (getPlayer().getEffectList().getEffectsCountForSkill(skill.getId()) <= 0) {
                ThreadPoolManager.getInstance().schedule(new Buff(this, getPlayer(), skill), delay);
                delay = delay + skill.getHitTime() + 500;
            }
        }
    }

    @Override
    protected void onDeath(final Creature killer) {
        super.onDeath(killer);
        if (_buffTask != null) {
            _buffTask.cancel(false);
            _buffTask = null;
        }
        final Player owner = getPlayer();
        if (owner != null && owner.getAgathion() == this) {
            owner.setAgathion(null);
        }
    }

    @Override
    public Player getPlayer() {
        return _playerRef.get();
    }

    public void setOwner(final Player owner) {
        _playerRef = ((owner == null) ? HardReferences.emptyRef() : owner.getRef());
        if (owner != null) {
            setTitle(owner.getName());
            if (owner.getAgathion() != null) {
                owner.getAgathion().doDespawn();
            }
            owner.setAgathion(this);
            for (final Player player : World.getAroundPlayers(this)) {
                player.sendPacket(new NpcInfo(this, player));
            }

            getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, owner);
            _buffTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new RunnableImpl() {
                @Override
                public void runImpl() {
                    buffOwner();
                }
            }, BUFF_INTERVAL, BUFF_INTERVAL);
        } else {
            doDespawn();
        }
    }

    public void doDespawn() {
        stopMove();
        if (_buffTask != null) {
            _buffTask.cancel(false);
            _buffTask = null;
        }
        final Player owner = getPlayer();
        if (owner != null && owner.getAgathion() == this) {
            owner.setAgathion(null);
        }
        setTarget(null);
        onDecay();
    }

    public static class Buff extends RunnableImpl {
        private final NpcInstance _actor;
        private final Player _owner;
        private final Skill _skill;

        public Buff(final NpcInstance actor, final Player owner, final Skill skill) {
            _actor = actor;
            _owner = owner;
            _skill = skill;
        }

        @Override
        public void runImpl() {
            if (_actor != null) {
                _actor.doCast(_skill, _owner, true);
            }
        }
    }
    @Override
    public boolean isAttackable(final Creature attacker) {
        return false;
    }
}
