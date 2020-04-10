package ru.l2.gameserver.network.lineage2.clientpackets;

import ru.l2.gameserver.model.Party;
import ru.l2.gameserver.model.Player;

public class AnswerPartyLootModification extends L2GameClientPacket {
    public int _answer;

    @Override
    protected void readImpl() {
        _answer = readD();
    }

    @Override
    protected void runImpl() {
        final Player activeChar = getClient().getActiveChar();
        if (activeChar == null) {
            return;
        }
        final Party party = activeChar.getParty();
        if (party != null) {
            party.answerLootChangeRequest(activeChar, _answer == 1);
        }
    }
}
