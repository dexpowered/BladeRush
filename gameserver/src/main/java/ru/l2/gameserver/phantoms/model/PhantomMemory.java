package ru.l2.gameserver.phantoms.model;

import ru.l2.gameserver.model.AggroList;

import java.util.ArrayList;
import java.util.List;

public class PhantomMemory {
    private final AggroList aggroList;
    private Phantom owner;
    private List<String> ignoredChatNicks = new ArrayList<>();

    public PhantomMemory(Phantom owner) {
        this.owner = owner;
        aggroList = new AggroList(owner);
    }

    public Phantom getOwner() {
        return owner;
    }

    public AggroList getAggroList() {
        return aggroList;
    }

    public void addIgnoredChatNick(final String nick) {
        ignoredChatNicks.add(nick);
    }

    public boolean isIgnoredChatNick(final String nick) {
        return ignoredChatNicks.contains(nick);
    }
}
