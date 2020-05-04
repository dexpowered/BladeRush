package ru.l2.gameserver.skills.skillclasses;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.data.cache.Msg;
import ru.l2.gameserver.data.xml.holder.NpcTemplateHolder;
import ru.l2.gameserver.idfactory.IdFactory;
import ru.l2.gameserver.model.*;
import ru.l2.gameserver.model.instances.AgathionInstance;
import ru.l2.gameserver.tables.SkillTable;
import ru.l2.gameserver.templates.StatsSet;
import ru.l2.gameserver.templates.npc.NpcTemplate;
import ru.l2.gameserver.utils.Location;

import java.util.List;

public class AgathionSummon extends Skill {

    public AgathionSummon(final StatsSet set) {
        super(set);
        _npcId = set.getInteger("npcId", 1);
        agathion_skill_rem = set.getInteger("skillRem", 1);
    }
    @Override
    public boolean checkCondition(final Creature activeChar, final Creature target, final boolean forceUse, final boolean dontMove, final boolean first) {
        final Player player = activeChar.getPlayer();
        if (player == null) {
            return false;
        }

        if (player.isInCombat()) {
            player.sendPacket(Msg.YOU_CANNOT_SUMMON_DURING_COMBAT);
            return false;
        }
        if (player.isProcessingRequest()) {
            player.sendPacket(Msg.PETS_AND_SERVITORS_ARE_NOT_AVAILABLE_AT_THIS_TIME);
            return false;
        }
        if (player.getAgathion() != null) {
            player.sendPacket(Msg.YOU_ALREADY_HAVE_A_PET);
            return false;
        }
        if (player.isInBoat()) {
            player.sendPacket(Msg.YOU_MAY_NOT_CALL_FORTH_A_PET_OR_SUMMONED_CREATURE_FROM_THIS_LOCATION);
            return false;
        }
        if (player.isOlyParticipant()) {
            player.sendPacket(Msg.THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT);
            return false;
        }
        if (player.isCursedWeaponEquipped()) {
            player.sendPacket(Msg.YOU_MAY_NOT_USE_MULTIPLE_PETS_OR_SERVITORS_AT_THE_SAME_TIME);
            return false;
        }
        for (final GameObject o : World.getAroundObjects(player, 120, 200)) {
            if (o.isDoor()) {
                player.sendPacket(Msg.YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION);
                return false;
            }
        }
        return super.checkCondition(activeChar, target, forceUse, dontMove, first);
    }

    @Override
    public void useSkill(final Creature caster, final List<Creature> targets) {
        final Player activeChar = caster.getPlayer();
        final NpcTemplate summonTemplate = NpcTemplateHolder.getInstance().getTemplate(getNpcId());
        final AgathionInstance summon = new AgathionInstance(IdFactory.getInstance().getNextId(), summonTemplate);

        summon.setTargetable(false);
        summon.setShowName(false);
        summon.setIsInvul(true);
        summon.setHeading(activeChar.getHeading());
        summon.setReflection(activeChar.getReflection());
        summon.spawnMe(Location.findAroundPosition(activeChar, 50, 70));
        summon.setOwner(activeChar);
        summon.setIsRunning(true);
        summon.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, activeChar, 1600);

        Skill unsummon = SkillTable.getInstance().getInfo(getAgathionSkillRem(), SkillTable.getInstance().getMaxLevel(getAgathionSkillRem()));
        activeChar.addSkill(unsummon, false);
        activeChar.sendSkillList();

        if (isSSPossible()) {
            caster.unChargeShots(isMagic());
        }
    }


}
