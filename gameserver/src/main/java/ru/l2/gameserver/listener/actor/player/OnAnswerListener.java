package ru.l2.gameserver.listener.actor.player;

import ru.l2.gameserver.listener.PlayerListener;

public interface OnAnswerListener extends PlayerListener {
    void sayYes();

    default void sayNo() {
    }

    /**
     * От тупого хака
     */
    default long expireTime() {
        return 0;
    }
}
