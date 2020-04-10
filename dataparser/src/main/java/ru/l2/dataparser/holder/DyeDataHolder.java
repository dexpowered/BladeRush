package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.dyedata.DyeData;

import java.util.List;

/**
 * @author : Camelion
 * @date : 27.08.12 1:36
 */
public class DyeDataHolder extends AbstractHolder {
    private static final DyeDataHolder ourInstance = new DyeDataHolder();
    @Element(start = "dye_begin", end = "dye_end")
    private List<DyeData> dyes;

    private DyeDataHolder() {
    }

    public static DyeDataHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return dyes.size();
    }

    public List<DyeData> getDyes() {
        return dyes;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}