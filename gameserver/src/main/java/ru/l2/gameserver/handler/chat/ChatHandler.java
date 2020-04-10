package ru.l2.gameserver.handler.chat;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.gameserver.network.lineage2.components.ChatType;

public class ChatHandler extends AbstractHolder {
    private static final ChatHandler _instance = new ChatHandler();

    private final IChatHandler[] _handlers;

    private ChatHandler() {
        _handlers = new IChatHandler[ChatType.VALUES.length];
    }

    public static ChatHandler getInstance() {
        return _instance;
    }

    public void register(final IChatHandler chatHandler) {
        _handlers[chatHandler.getType().ordinal()] = chatHandler;
    }

    public IChatHandler getHandler(final ChatType type) {
        return _handlers[type.ordinal()];
    }

    @Override
    public int size() {
        return _handlers.length;
    }

    @Override
    public void clear() {
    }
}
