package ru.l2.gameserver.phantoms.template;

import java.util.ArrayList;
import java.util.List;

public class PhantomArmorTemplate {
    private final List<Integer> itemIds;

    public PhantomArmorTemplate() {
        itemIds = new ArrayList<>();
    }

    public void addId(final int id) {
        itemIds.add(id);
    }

    public List<Integer> getIds() {
        return itemIds;
    }
}
