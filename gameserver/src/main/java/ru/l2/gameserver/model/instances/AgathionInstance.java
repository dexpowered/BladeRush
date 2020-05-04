package ru.l2.gameserver.model.instances;

import ru.l2.commons.lang.reference.HardReference;
import ru.l2.commons.lang.reference.HardReferences;
import ru.l2.commons.threading.RunnableImpl;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.ThreadPoolManager;
import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.*;
import ru.l2.gameserver.network.lineage2.components.ChatType;
import ru.l2.gameserver.network.lineage2.serverpackets.*;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.Location;

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
        int _numBuffs =0 ;
        // check if the owner is no longer around...if so, despawn
        if ((getPlayer() == null) || (getPlayer().isDead())) {
            doDespawn();
            return;
        }

        if (!isInRange(getPlayer(), MAX_DISTANCE_FOR_BUFF)) {
            getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, getPlayer());
            return;
        }
        int delay = 0;
        /*
        for (final Skill skill : getTemplate().getBuffSkills()) {
            if (getPlayer().getEffectList().getEffectsCountForSkill(skill.getId()) <= 0) {
                ThreadPoolManager.getInstance().schedule(new Buff(this, getPlayer(), skill), delay);
                delay = delay + skill.getHitTime() + 500;
            }
        }
*/
        boolean warmOffMsg = false;
        for (final Skill sk : getTemplate().getBuffSkills()) {
            final AgathionInstance actor = this;
            for (final Effect e : getPlayer().getEffectList().getAllEffects()) {
                if (e.getSkill().getId() == sk.getId()) {
                    e.exit();
                    warmOffMsg = true;
                }
            }

            actor.broadcastPacket(new MagicSkillLaunched(actor, sk, getPlayer().getObjectId()));
            actor.broadcastPacket(new MagicSkillUse(actor, getPlayer(), sk.getDisplayId(), sk.getDisplayLevel(), sk.getHitTime(), 0L));

            if (warmOffMsg) {
                    this.sendPacket(new SystemMessage(92).addSkillName(sk.getDisplayId(), sk.getDisplayLevel()));
                }
                if (Config.ALT_NPC_BUFFER_EFFECT_TIME > 0L) {
                    sk.getEffects(this, getPlayer(), false, false, Config.ALT_NPC_BUFFER_EFFECT_TIME, 1.0, false);
                } else {
                    sk.getEffects(this, getPlayer(), false, false);
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
            broadcastPacket(new NpcSay(this, ChatType.TELL, "Спасибо, " + owner.getName() + ". Я надеюсь что я смогу помочь Вам!"));

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
        for (Skill skill : getTemplate().getBuffSkills()) {
            for (final Effect e : getPlayer().getEffectList().getAllEffects()) {
                if (e.getSkill().getId() == skill.getId()) {
                    e.exit();
                }
            }
        }

        final Player owner = getPlayer();
        if (owner != null && owner.getAgathion() == this) {
            owner.setAgathion(null);
        }




        setTarget(null);
        onDecay();
    }

    public void teleportToOwner() {
        final Player owner = getPlayer();
        //setNonAggroTime(System.currentTimeMillis() + Config.NONAGGRO_TIME_ONTELEPORT);
        if (owner.isOlyParticipant()) {
            teleToLocation(owner.getLoc(), owner.getReflection());
        } else {
            teleToLocation(Location.findPointToStay(owner, 50, 150), owner.getReflection());
        }
        if (!isDead()) {
            getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, owner);
        }
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
