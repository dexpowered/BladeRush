package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.commons.lang.ArrayUtils;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.xml.holder.SkillAcquireHolder;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.SkillLearn;
import ru.l2.gameserver.model.base.AcquireType;
import ru.l2.gameserver.model.base.ClassId;
import ru.l2.gameserver.model.instances.NpcInstance;
import ru.l2.gameserver.network.lineage2.serverpackets.AcquireSkillInfo;
import ru.l2.gameserver.tables.SkillTable;

public class RequestAquireSkillInfo extends L2GameClientPacket {
    private int _id;
    private int _level;
    private AcquireType _type;

    @Override
    protected void readImpl() {
        _id = readD();
        _level = readD();
        _type = ArrayUtils.valid(AcquireType.VALUES, readD());
    }

    @Override
    protected void runImpl() {
        final Player player = getClient().getActiveChar();
        if (player == null || player.getTransformation() != 0 || SkillTable.getInstance().getInfo(_id, _level) == null || _type == null) {
            return;
        }
        final NpcInstance trainer = player.getLastNpc();
        if (trainer == null || !trainer.isInActingRange(player)) {
            return;
        }
        final int clsId = player.getVarInt("AcquireSkillClassId", player.getClassId().getId());
        final ClassId classId = (clsId >= 0 && clsId < ClassId.VALUES.length) ? ClassId.VALUES[clsId] : null;
        final SkillLearn skillLearn = SkillAcquireHolder.getInstance().getSkillLearn(player, classId, _id, _level, _type);
        if (skillLearn == null) {
            return;
        }

        if (Config.ALT_ITEM_LEARN_MODE && _type == AcquireType.NORMAL) {
            int count_item_learn = Config.ALT_ITEM_LEARN_COUNT;
            int item_learn = Config.ALT_ITEM_LEARN_ID;


            if(Config.ALT_ITEM_LEARN_SP)
            {
                if(skillLearn.getCost() == 0){
                    count_item_learn = 1;
                }else
                    count_item_learn = skillLearn.getCost();
            }

            for (Config.AltSkillPrice config : Config.ALT_PRICE_SKILL) {

                if(skillLearn.getId() == config.skillId)
                {
                    item_learn = config.priceId;
                    count_item_learn = config.priceCount;
                }
            }

            sendPacket(new AcquireSkillInfo(_type, skillLearn, item_learn, count_item_learn));
        } else {
            sendPacket(new AcquireSkillInfo(_type, skillLearn, 0, 0));
        }
    }
}
