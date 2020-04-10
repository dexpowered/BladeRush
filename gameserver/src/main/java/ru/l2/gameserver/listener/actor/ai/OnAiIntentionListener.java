package ru.l2.gameserver.listener.actor.ai;

import ru.l2.gameserver.ai.CtrlIntention;
import ru.l2.gameserver.listener.AiListener;
import ru.l2.gameserver.model.Creature;

public interface OnAiIntentionListener extends AiListener {
    void onAiIntention(final Creature p0, final CtrlIntention p1, final Object p2, final Object p3);
}
