package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.monrace.MonRace;
import ru.l2.dataparser.holder.monrace.MonRuner;
import ru.l2.dataparser.holder.monrace.MonTrack;

import java.util.List;

/**
 * @author : Camelion
 * @date : 30.08.12 14:02
 */
public class MonraceHolder extends AbstractHolder {
    private static final MonraceHolder ourInstance = new MonraceHolder();
    @Element(start = "race_begin", end = "race_end")
    private MonRace monRace;
    @Element(start = "track_begin", end = "track_end")
    private List<MonTrack> monTracks;
    @Element(start = "mon_begin", end = "mon_end")
    private List<MonRuner> monRuners;

    private MonraceHolder() {
    }

    public static MonraceHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return 1 + monTracks.size() + monRuners.size();
    }

    public MonRace getMonRace() {
        return monRace;
    }

    public List<MonTrack> getMonTracks() {
        return monTracks;
    }

    public List<MonRuner> getMonRuners() {
        return monRuners;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}