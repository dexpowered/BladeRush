package ru.l2.gameserver.handler.chat;

import ru.l2.gameserver.network.lineage2.components.ChatType;

public interface IChatHandler {
    void say();

    ChatType getType();
}
