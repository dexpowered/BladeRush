package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.minigame.BlockupsetSettings;

/**
 * @author : Camelion
 * @date : 30.08.12 13:33
 */
public class MinigameHolder extends AbstractHolder {
    private static final MinigameHolder ourInstance = new MinigameHolder();
    @Element(start = "blockupset_setting_begin", end = "blockupset_setting_end")
    private BlockupsetSettings blockupsetSettings;

    private MinigameHolder() {
    }

    public static MinigameHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return 1;
    }

    public BlockupsetSettings getBlockupsetSettings() {
        return blockupsetSettings;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}