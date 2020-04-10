package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.multisell.Multisell;

import java.util.List;

/**
 * @author : Camelion
 * @date : 30.08.12 14:23
 */
public class MultisellHolder extends AbstractHolder {
    private static final MultisellHolder ourInstance = new MultisellHolder();
    @Element(start = "MultiSell_begin", end = "MultiSell_end")
    private List<Multisell> multisells;

    private MultisellHolder() {
    }

    public static MultisellHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return multisells.size();
    }

    public List<Multisell> getMultisells() {
        return multisells;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}