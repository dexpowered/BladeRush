package ru.custom.phantoms.data.holder;

import ru.custom.phantoms.model.Phantom;
import ru.l2.commons.data.xml.AbstractHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PhantomOnlineHolder extends AbstractHolder {
    private static final PhantomOnlineHolder instance = new PhantomOnlineHolder();

    private final Map<String, Phantom> phantoms;

    public PhantomOnlineHolder() {
        phantoms = new ConcurrentHashMap<>();
    }

    public static PhantomOnlineHolder getInstance() {
        return PhantomOnlineHolder.instance;
    }

    public void addPhantom(final Phantom phantom) {
        phantoms.put(phantom.getName(), phantom);
    }

    public void deletePhantom(final Phantom phantom) {
        deletePhantom(phantom.getName());
    }

    public void deletePhantom(final String name) {
        phantoms.remove(name);
    }

    public void getPhantom(final String name) {
        phantoms.get(name);
    }

    public Map<String, Phantom> getPhantomMap() {
        return phantoms;
    }

    public boolean contains(final Phantom phantom) {
        return contains(phantom.getName());
    }

    public boolean contains(final String name) {
        return phantoms.keySet().contains(name);
    }

    public boolean contains(final int objId) {
        return phantoms.values().stream().anyMatch(phantom -> phantom.getObjectId() == objId);
    }

    @Override
    public int size() {
        return phantoms.size();
    }

    @Override
    public void clear() {
        phantoms.clear();
    }
}
