package ru.l2.gameserver.listener.actor.ai;

import ru.l2.gameserver.ai.CtrlEvent;
import ru.l2.gameserver.listener.AiListener;
import ru.l2.gameserver.model.Creature;

public interface OnAiEventListener extends AiListener {
    void onAiEvent(final Creature p0, final CtrlEvent p1, final Object[] p2);
}
