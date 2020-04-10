package ru.l2.gameserver.listener.game;

import ru.l2.gameserver.listener.GameListener;

public interface OnSSPeriodListener extends GameListener {
    void onPeriodChange(final int p0);
}
