package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.Skill;

public class RequestTargetCanceld extends L2GameClientPacket {
    private int _unselect;

    @Override
    protected void readImpl() {
        _unselect = readH();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        if (_unselect == 0) {
            if (activeChar.isCastingNow()) {
                final Skill skill = activeChar.getCastingSkill();
                activeChar.abortCast(skill != null && (skill.isHandler() || skill.getHitTime() > 1000), false);
            } else if (activeChar.getTarget() != null) {
                activeChar.setTarget(null);
            }
        } else if (activeChar.getTarget() != null) {
            activeChar.setTarget(null);
        }
    }
}
