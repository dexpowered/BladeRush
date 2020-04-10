package ru.l2.gameserver.listener.game;

import ru.l2.gameserver.listener.GameListener;

public interface OnShutdownListener extends GameListener {
    void onShutdown();
}
