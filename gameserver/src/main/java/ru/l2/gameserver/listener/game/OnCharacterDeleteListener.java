package ru.l2.gameserver.listener.game;

import ru.l2.gameserver.listener.GameListener;

public interface OnCharacterDeleteListener extends GameListener {
    void onCharacterDelate(final int p0);
}
