package ru.l2.gameserver.model.instances;

import gnu.trove.set.hash.TIntHashSet;
import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.model.Creature;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.network.lineage2.serverpackets.MagicSkillUse;
import ru.l2.gameserver.network.lineage2.serverpackets.MyTargetSelected;
import ru.l2.gameserver.network.lineage2.serverpackets.ValidateLocation;
import ru.l2.gameserver.scripts.Events;
import ru.l2.gameserver.tables.SkillTable;
import ru.l2.gameserver.templates.npc.NpcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class OlympiadBufferInstance extends NpcInstance {
    private final TIntHashSet buffs;

    public OlympiadBufferInstance(final int objectId, final NpcTemplate template) {
        super(objectId, template);
        buffs = new TIntHashSet();
    }

    @Override
    public void onAction(final Player player, final boolean shift) {
        if (Events.onAction(player, this, shift)) {
            player.sendActionFailed();
            return;
        }
        if (this != player.getTarget()) {
            player.setTarget(this);
            final MyTargetSelected my = new MyTargetSelected(getObjectId(), player.getLevel() - getLevel());
            player.sendPacket(my);
            player.sendPacket(new ValidateLocation(this));
        } else {
            final MyTargetSelected my = new MyTargetSelected(getObjectId(), player.getLevel() - getLevel());
            player.sendPacket(my);
            if (!isInActingRange(player)) {
                if (!player.getAI().isIntendingInteract(this)) {
                    player.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, this);
                }
            } else if (buffs.size() > 4) {
                showChatWindow(player, 1);
            } else {
                showChatWindow(player, 0);
            }
            player.sendActionFailed();
        }
    }

    @Override
    public void onBypassFeedback(final Player player, final String command) {
        if (!NpcInstance.canBypassCheck(player, this)) {
            return;
        }
        if (buffs.size() > 4) {
            showChatWindow(player, 1);
        }
        if (command.startsWith("Buff")) {
            int id;
            int lvl;
            final StringTokenizer st = new StringTokenizer(command, " ");
            st.nextToken();
            id = Integer.parseInt(st.nextToken());
            lvl = Integer.parseInt(st.nextToken());
            final Skill skill = SkillTable.getInstance().getInfo(id, lvl);
            final List<Creature> target = new ArrayList<>();
            target.add(player);
            broadcastPacket(new MagicSkillUse(this, player, id, lvl, 0, 0L));
            callSkill(skill, target, true);
            buffs.add(id);
            if (buffs.size() > 4) {
                showChatWindow(player, 1);
            } else {
                showChatWindow(player, 0);
            }
        } else {
            showChatWindow(player, 0);
        }
    }

    @Override
    public String getHtmlPath(final int npcId, final int val, final Player player) {
        String pom;
        if (val == 0) {
            pom = "buffer";
        } else {
            pom = "buffer-" + val;
        }
        return "olympiad/" + pom + ".htm";
    }
}
