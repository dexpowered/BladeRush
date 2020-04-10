package ru.l2.gameserver.model.chat.chatfilter;

import ru.l2.gameserver.network.lineage2.components.ChatType;

public class ChatMsg {
    public final ChatType chatType;
    public final int recipient;
    public final int msgHashcode;
    public final int time;

    public ChatMsg(final ChatType chatType, final int recipient, final int msgHashcode, final int time) {
        this.chatType = chatType;
        this.recipient = recipient;
        this.msgHashcode = msgHashcode;
        this.time = time;
    }
}
