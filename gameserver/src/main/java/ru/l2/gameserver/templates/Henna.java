package ru.l2.gameserver.templates;

import gnu.trove.list.array.TIntArrayList;
import ru.l2.gameserver.model.Player;

public class Henna {
    private final int _symbolId;
    private final int _dyeId;
    private final long _price;
    private final long _drawCount;
    private final int _statINT;
    private final int _statSTR;
    private final int _statCON;
    private final int _statMEN;
    private final int _statDEX;
    private final int _statWIT;
    private final TIntArrayList _classes;

    public Henna(final int symbolId, final int dyeId, final long price, final long drawCount, final int wit, final int intA, final int con, final int str, final int dex, final int men, final TIntArrayList classes) {
        _symbolId = symbolId;
        _dyeId = dyeId;
        _price = price;
        _drawCount = drawCount;
        _statINT = intA;
        _statSTR = str;
        _statCON = con;
        _statMEN = men;
        _statDEX = dex;
        _statWIT = wit;
        _classes = classes;
    }

    public int getSymbolId() {
        return _symbolId;
    }

    public int getDyeId() {
        return _dyeId;
    }

    public long getPrice() {
        return _price;
    }

    public int getStatINT() {
        return _statINT;
    }

    public int getStatSTR() {
        return _statSTR;
    }

    public int getStatCON() {
        return _statCON;
    }

    public int getStatMEN() {
        return _statMEN;
    }

    public int getStatDEX() {
        return _statDEX;
    }

    public int getStatWIT() {
        return _statWIT;
    }

    public boolean isForThisClass(final Player player) {
        return _classes.contains(player.getActiveClassId());
    }

    public long getDrawCount() {
        return _drawCount;
    }
}
